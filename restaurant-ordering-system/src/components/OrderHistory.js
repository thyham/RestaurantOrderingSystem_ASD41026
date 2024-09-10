import React, { useState, useEffect } from "react";

function OrderHistory() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    // Mock order data for testing
    const mockOrders = [
      {
        id: 1,
        date: '2023-09-09',
        status: 'Delivered',
        items: [
          { id: 1, name: 'Pizza', price: 10 },
          { id: 2, name: 'Burger', price: 8 }
        ]
      },
      {
        id: 2,
        date: '2023-09-10',
        status: 'Shipped',
        items: [
          { id: 3, name: 'Pasta', price: 12 },
          { id: 4, name: 'Salad', price: 6 }
        ]
      }
    ];

    // Set mock orders to state
    setOrders(mockOrders);
  }, []);

  const reorder = (orderId) => {
    alert(`Reordered items from order #${orderId}`);
  };

  return (
    <div>
      <h1>Order History</h1>
      <ul>
        {orders.map((order) => (
          <li key={order.id}>
            <h2>Order #{order.id}</h2>
            <p>Date: {order.date}</p>
            <p>Status: {order.status}</p>
            <button onClick={() => reorder(order.id)}>Reorder</button>
            <h3>Items:</h3>
            <ul>
              {order.items.map((item) => (
                <li key={item.id}>
                  {item.name} - ${item.price}
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default OrderHistory;
