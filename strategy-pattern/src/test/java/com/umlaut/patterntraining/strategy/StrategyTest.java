package com.umlaut.patterntraining.strategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.umlaut.patterntraining.strategy.ItemTestdatenErzeuger.standardItemBuilder;
import static com.umlaut.patterntraining.strategy.StockTestdatenErzeuger.standardStock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class StrategyTest {

    private static AutoCloseable annotations;
    @Captor
    private ArgumentCaptor<Map.Entry<Item, Integer>> itemCaptor;
    @Captor
    private ArgumentCaptor<Integer> indexCaptor;
    @Mock
    private ItemPrinter itemPrinter;
    @InjectMocks
    private StockPrinter stockPrinter;

    private Reflections prepareReflections() {
        // find all classes defined in the package of interest
        String packageToAnalyze = "com.umlaut.patterntraining.strategy";

        return new Reflections(
                new ConfigurationBuilder()
                        .forPackage(packageToAnalyze)
                        .setScanners(Scanners.SubTypes.filterResultsBy(c -> true))
        );
    }

    private Set<Class<?>> getStrategyInterfaceCandidates(Reflections reflections) {
        //make sure an appropriate interface is defined
        var interfaces = reflections.getSubTypesOf(Object.class).stream().filter(Class::isInterface);
        var methods = interfaces.map(Class::getMethods).map(Arrays::asList).flatMap(List::stream);
        var methodCandidates = methods
                .filter(m -> m.getReturnType().getName().equals("java.lang.Iterable"))
                .filter(m -> m.getParameterTypes().length == 1)
                .filter(m -> m.getParameterTypes()[0].getName().equals("java.util.Map"));
        return methodCandidates.map(Method::getDeclaringClass).filter(Class::isInterface).collect(Collectors.toSet());
    }

    @Before
    public void init() {
        annotations = MockitoAnnotations.openMocks(this);
    }

    @After
    public void cleanUp() throws Exception {
        annotations.close();
    }

    @Test
    public void inputOfNull_results_in_exception() {

        assertThatThrownBy(() -> stockPrinter.print(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("stock is not defined");
    }

    @Test
    public void defaultBehaviour_prints_in_native_order() {
        // given
        Map<Item, Integer> stock = standardStock(4);

        // when
        stockPrinter.print(stock);

        // then
        verify(itemPrinter, times(4)).printItem(indexCaptor.capture(), itemCaptor.capture());

        assertThat(indexCaptor.getAllValues()).containsExactly(1, 2, 3, 4);
        assertThat(itemCaptor.getAllValues()).containsExactlyElementsOf(stock.entrySet());
    }

    @Test
    public void structural_implementation_strategy() {
        Reflections reflections = prepareReflections();
        Set<Class<?>> matchedInterfaces = getStrategyInterfaceCandidates(reflections);

        assertThat(matchedInterfaces.size()).as("at least one interface contains a method of appropriate signature").isGreaterThanOrEqualTo(1);

        Set<Class<?>> matchedImplementations = matchedInterfaces.stream()
                .flatMap(interf -> reflections.getSubTypesOf(interf).stream())
                .collect(Collectors.toSet());
        assertThat(matchedImplementations.size()).as("at least one implementation of an appropriate interface exists").isGreaterThanOrEqualTo(1);

        // check if the stock printer holds an instance of the interfaces
        List<Field> fields = new ArrayList<>(Arrays.asList(StockPrinter.class.getDeclaredFields()));

        List<Class<?>> typesOfFields = fields.stream().map(Field::getType).collect(Collectors.toList());
        assertThat(typesOfFields).as(StockPrinter.class.getName() + " holds field of at least one of the strategy interface candidates").containsAnyElementsOf(matchedInterfaces);

        // check if there is a setter for an instance of the interfaces
        var setterCandidates = Arrays.stream(StockPrinter.class.getMethods())
                .filter(m -> m.getName().contains("set"))
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .filter(m -> m.getReturnType().equals(void.class))
                .filter(m -> Arrays.stream(m.getParameterTypes()).anyMatch(matchedInterfaces::contains))
                .collect(Collectors.toSet());
        assertThat(setterCandidates.size()).as("There is a setter for at least one of the strategy interface candidates [" + String.join(", ", matchedInterfaces.stream().map(Class::toString).collect(Collectors.toSet())) + "]").isGreaterThanOrEqualTo(1);
    }

    @Test
    public void sort_by_name_prints_in_alphabetic_order() {

        Reflections reflections = prepareReflections();

        Set<Class<?>> matchedInterfaces = getStrategyInterfaceCandidates(reflections);

        // search for an implementation of the interfaces
        var pattern = Pattern.compile(".*by.*name.*", Pattern.CASE_INSENSITIVE);
        Set<Class<?>> implementations = matchedInterfaces.stream()
                .flatMap(interf -> reflections.getSubTypesOf(interf).stream().filter(cl -> pattern.matcher((cl).getSimpleName()).find()))
                .collect(Collectors.toSet());

        assertThat(implementations.size()).as("at least one implementation of an appropriate interface exists and matches the regex \""+pattern.pattern()+"\"").isGreaterThanOrEqualTo(1);

        implementations.forEach(aClass -> {
            try {
                var constructor = aClass.getConstructor();
                var instance = (ITestStrategy) constructor.newInstance();

                // given
                Map<Item, Integer> stock = standardStock(
                        standardItemBuilder().withName("Apple").build(),
                        standardItemBuilder().withName("Pear").build(),
                        standardItemBuilder().withName("Egg").build(),
                        standardItemBuilder().withName("Strawberry").build()
                );

                // when
                stockPrinter.setStrategy(instance);
                stockPrinter.print(stock);

                // Then
                verify(itemPrinter, times(4)).printItem(indexCaptor.capture(), itemCaptor.capture());

                assertThat(indexCaptor.getAllValues()).containsExactly(1, 2, 3, 4);
                assertThat(itemCaptor.getAllValues()).extracting(item -> item.getKey().getName()).containsExactly("Apple", "Egg", "Pear", "Strawberry");

                reset(itemPrinter);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                // ignore these exceptions as they are kind of expected, and we are happy if a single verification succeeds.
            }
        });
    }
}
