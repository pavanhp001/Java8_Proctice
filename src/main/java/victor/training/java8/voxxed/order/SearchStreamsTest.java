package victor.training.java8.voxxed.order;

import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import victor.training.java8.voxxed.order.entity.Customer;
import victor.training.java8.voxxed.order.entity.Order;
import victor.training.java8.voxxed.order.entity.Order.Status;
import victor.training.java8.voxxed.order.entity.OrderLine;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchStreamsTest {

	private SearchStreams service = new SearchStreams();

	@Test
	public void p1_getActiveOrders() {
		Order order1 = new Order(Status.ACTIVE);
		Order order2 = new Order(Status.ACTIVE);
		Order order3 = new Order(Status.DRAFT);
		Customer customer = new Customer(order1, order2, order3);
		
		System.out.println("p1_getActiveOrders="+service.p1_getActiveOrders(customer));
		assertEquals(Arrays.asList(order1, order2), service.p1_getActiveOrders(customer));
	}

	@Test
	public void p2_getOrderById() {
		List<Order> orders = Arrays.asList(new Order(1L), new Order(2L), new Order(3L));
		System.out.println("p2_getOrderById="+(long) service.p2_getOrderById(orders, 1L).getId());
		assertEquals(1L, (long) service.p2_getOrderById(orders, 1L).getId());
	}
	
	@Test
	@Ignore
	public void p2_getOrderById_whenIdNotFound() {
		List<Order> orders = Arrays.asList(new Order(1L));
		assertEquals(null, (long) service.p2_getOrderById(orders, 1000L).getId());
	}

	@Test
	public void p3_hasActiveOrders_true() {
		Customer customer = new Customer(new Order(Status.INACTIVE), new Order(Status.ACTIVE));
		System.out.println("p3_hasActiveOrders="+service.p3_hasActiveOrders(customer));
		assertTrue(service.p3_hasActiveOrders(customer));
	}

	@Test
	public void p3_hasActiveOrders_false() {
		Customer customer = new Customer(new Order(Status.INACTIVE));
		assertFalse(service.p3_hasActiveOrders(customer));
	}
	
	@Test
	public void p4_canBeReturned_true() {
		Order order = new Order(new OrderLine());
		assertTrue(service.p4_canBeReturned(order));
	}
	
	@Test
	public void p4_canBeReturned_false() {
		OrderLine specialOffer = new OrderLine().setSpecialOffer(true);
		Order order = new Order(specialOffer, new OrderLine());
		assertFalse(service.p4_canBeReturned(order));
	}
	
	@Test
	public void p5_getMaxPriceOrder() {
		LocalDate yesterday = now().minusDays(1);
		Order order1 = new Order().setTotalPrice(BigDecimal.ONE).setCreationDate(now());
		Order order2 = new Order().setTotalPrice(BigDecimal.TEN).setCreationDate(yesterday);
		//assertEquals(order2, service.p5_getMaxPriceOrder(new Customer(order1, order2)));
		 assertEquals(yesterday, service.p5_getMaxPriceOrder(new Customer(order1, order2)).get());
	}
	
	@Test
	public void p5_getMaxPriceOrder_whenNoOrders_returnsNothing() {
		//assertNull(service.p5_getMaxPriceOrder(new Customer()));
		 assertFalse(service.p5_getMaxPriceOrder(new Customer()).isPresent());
	}
	
	@Test
	public void p6_getLast3Orders() {
		Order order1 = new Order().setCreationDate(LocalDate.parse("2016-01-01"));
		Order order2 = new Order().setCreationDate(LocalDate.parse("2016-01-02"));
		Order order3 = new Order().setCreationDate(LocalDate.parse("2016-01-03"));
		Order order4 = new Order().setCreationDate(LocalDate.parse("2016-01-04"));
		
		Customer customer = new Customer(order1, order2, order3, order4);
		System.out.println("getLast3Orders="+service.p6_getLast3Orders(customer));
		assertEquals(Arrays.asList(order4,order3,order2), service.p6_getLast3Orders(customer));
	}
	
	@Test
	public void p6_getLast3Orders_whenOnlyTwoOrders() {
		Order order1 = new Order().setCreationDate(LocalDate.parse("2016-01-01"));
		Order order2 = new Order().setCreationDate(LocalDate.parse("2016-01-02"));
		
		Customer customer = new Customer(order1, order2);
		assertEquals(Arrays.asList(order2, order1), service.p6_getLast3Orders(customer));
	}
	
	@Test
	public void p6_getLast3Orders_whenNoOrders() {
		Customer customer = new Customer();
		assertEquals(Arrays.asList(), service.p6_getLast3Orders(customer));
	}
	

}
