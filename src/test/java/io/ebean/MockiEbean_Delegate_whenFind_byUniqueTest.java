package io.ebean;

import org.example.domain.Customer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MockiEbean_Delegate_whenFind_byUniqueTest extends BaseTest {

  final Customer foo = new Customer(42L, "foo");

  @Test
  public void byUnique() throws Exception {

    io.ebean.DelegateEbeanServer mock = new io.ebean.DelegateEbeanServer();
    mock.whenFind().byUnique(Customer.class).thenReturn(foo);

    MockiEbean.runWithMock(mock, () -> {

      Customer found = Customer.find.byUniqueName("junk");
      assertThat(found).isSameAs(foo);

      Customer viaFinder = Customer.find.byUniqueName("foo");
      assertThat(viaFinder).isSameAs(foo);
    });

  }

}