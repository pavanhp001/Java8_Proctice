package victor.training.java8.voxxed.order;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import victor.training.java8.voxxed.order.entity.Customer;
import victor.training.java8.voxxed.order.entity.Order;
import victor.training.java8.voxxed.order.entity.Order.Status;
import victor.training.java8.voxxed.order.entity.OrderLine;

public class SearchStreams {
	
	/**
	 * FIRST OF ALL: Add the following "Favourite" static imports:
	 * Eclipse: Window > Preferences > type "Favo" > Favorites >
	 * 					> New Type > Browse > and type the class name for:
		java.util.stream.Collectors
	 */
	
	/**
	 * - shorten/clean it up
	 */
	public List<Order> p1_getActiveOrders(Customer customer) {	
		return customer.getOrders().stream().filter(isActiveOrder()).collect(toList()); 
				
	}

	private Predicate<? super Order> isActiveOrder() {
		return order -> order.getStatus() == Order.Status.ACTIVE;
	}
	
	/**
	 * @return the Order in the list with the given id  
	 * - ...Any or ...First ?
	 * - what do you do when you don't find it ? null/throw/Optional ?
	 */
	public Order p2_getOrderById(List<Order> orders, long orderId) {
		return orders.stream().filter(order -> order.getId() == orderId).findFirst().get();
		
		//return null; // orders.stream()
	}
	
	/**
	 * @return true if customer has at least one order with status ACTIVE
	 */
	public boolean p3_hasActiveOrders(Customer customer) {
		
		return customer.getOrders().stream().anyMatch(isActiveOrder());
		//return true; 
	}

	/**
	 * An Order can be returned if it doesn't contain 
	 * any OrderLine with isSpecialOffer()==true
	 */
	public boolean p4_canBeReturned(Order order) {
		//return true; // 
		
		return order.getOrderLines().stream().noneMatch(isOrderLineHasSpecialOffer());
	}

	private Predicate<? super OrderLine> isOrderLineHasSpecialOffer() {
		return lineItem -> lineItem.isSpecialOffer()==true;
	}
	
	// ---------- select the best ------------
	
	/**
	 * The Order with maximum getTotalPrice. 
	 * i.e. the most expensive Order, or null if no Orders
	 * - Challenge: return an Optional<creationDate>
	 */
	public Optional<LocalDate> p5_getMaxPriceOrder(Customer customer) {
		
		//Comparator<Order> comparator = Comparator.comparing(Order::getTotalPrice);
		
		return customer.getOrders().stream().max(Comparator.comparing(Order::getTotalPrice)).map(Order::getCreationDate);
		//return null; 
	}
	
	/**
	 * last 3 Orders sorted descending by creationDate
	 */
	public List<Order> p6_getLast3Orders(Customer customer) {
		return customer.getOrders().stream().sorted(Comparator.comparing(Order::getCreationDate).reversed()).limit(3).collect(toList());
		
		//return null; 
	}
	
	
}
