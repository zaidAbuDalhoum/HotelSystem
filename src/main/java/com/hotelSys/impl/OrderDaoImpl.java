package com.hotelSys.impl;

import com.hotelSys.dao.OrderDao;
import com.hotelSys.model.Item;
import com.hotelSys.model.Order;
import com.hotelSys.model.OrderWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = Logger.getLogger(OrderDaoImpl.class);


    //order is added to Db by user
    @Override
    public void addOrder(final OrderWrapper orderWrapper, String type) {
        String INSERT = "INSERT INTO orders (userId,userName,roomId,billDescription,billAmount,tax," +
                "billAmountWithTax,orderStatus,orderDate,orderType)VALUES(?,?,?,?,?,?,?,?,?,?)";

        ArrayList<Item> items = new ArrayList<>();
        Item item;

        for (int i = 0; i < orderWrapper.getItems().size(); i++) {
            item = getItemsById(orderWrapper.getItems().get(i)).get(0);
            item.setQuantity(orderWrapper.getQuantity().get(i));
            items.add(item);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();


        final Order order = new Order();

        orderWrapper.setTax(0.16);
        order.setTax(0.16);

        List<Double> sums = calculateBill(items);

        order.setUserId(orderWrapper.getUserId());
        order.setUserName(orderWrapper.getUserName());
        order.setRoomId(orderWrapper.getRoomId());
        order.setBillAmount(sums.get(0));
        order.setBillAmountWithTax(sums.get(1));
        order.setBill(generateBillDescription(items));
        order.setOrderStatus("Active/Pending");
        order.setOrderDate(format.format(date.getTime()));
        order.setOrderType(type);


        jdbcTemplate.update(INSERT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, order.getUserId());
                preparedStatement.setString(2, order.getUserName());
                preparedStatement.setString(3, order.getRoomId());
                preparedStatement.setString(4, order.getBill());
                preparedStatement.setDouble(5, order.getBillAmount());
                preparedStatement.setDouble(6, order.getTax());
                preparedStatement.setDouble(7, order.getBillAmountWithTax());
                preparedStatement.setString(8, order.getOrderStatus());
                preparedStatement.setString(9, order.getOrderDate());
                preparedStatement.setString(10, order.getOrderType());
            }
        });


    }
    //delete order by user
    @Override
    public void deleteOrder(final int id) {

        String DELETE = "DELETE FROM orders WHERE orderId = ? ";

        jdbcTemplate.update(DELETE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        });

    }
    //get the last 100 orders registered to the system for admin
    @Override
    public List<Order> viewAllOrders(final String type) {

        String SELECT;

        if (type.equalsIgnoreCase("all")) {
            SELECT = "SELECT * FROM orders ORDER BY orderId ASC LIMIT 100;";
            return jdbcTemplate.query(SELECT, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setBill(resultSet.getString("billDescription"));
                    order.setBillAmount(resultSet.getDouble("billAmount"));
                    order.setBillAmountWithTax(resultSet.getDouble("billAmountWithTax"));
                    order.setOrderDate(resultSet.getString("orderDate"));
                    order.setUserName(resultSet.getString("userName"));
                    order.setRoomId(resultSet.getString("roomId"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setOrderStatus(resultSet.getString("orderStatus"));
                    order.setOrderType(resultSet.getString("orderType"));

                    return order;
                }
            });

        } else {
            SELECT = "SELECT * FROM orders WHERE orderType = ? ORDER BY orderId ASC LIMIT 100";
            return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, type);
                }
            }, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setBill(resultSet.getString("billDescription"));
                    order.setBillAmount(resultSet.getDouble("billAmount"));
                    order.setBillAmountWithTax(resultSet.getDouble("billAmountWithTax"));
                    order.setOrderStatus(resultSet.getString("orderDate"));
                    order.setUserName(resultSet.getString("userName"));
                    order.setRoomId(resultSet.getString("roomId"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setOrderStatus(resultSet.getString("orderStatus"));
                    order.setOrderType(resultSet.getString("orderType"));

                    return order;
                }
            });
        }


    }
    //get the pending orders for employees
    @Override
    public List<Order> viewPendingOrders(final String type) {

        String SELECT;

        if (type.equalsIgnoreCase("all")) {
            SELECT = "SELECT * FROM orders WHERE orderStatus = 'Active/Pending'";

            return jdbcTemplate.query(SELECT, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setBill(resultSet.getString("billDescription"));
                    order.setBillAmount(resultSet.getDouble("billAmount"));
                    order.setBillAmountWithTax(resultSet.getDouble("billAmountWithTax"));
                    order.setOrderDate(resultSet.getString("orderDate"));
                    order.setUserName(resultSet.getString("userName"));
                    order.setRoomId(resultSet.getString("roomId"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setOrderStatus(resultSet.getString("orderStatus"));

                    return order;

                }
            });


        } else{
            SELECT = "SELECT * FROM orders WHERE orderStatus = 'Active/Pending' and orderType = ?";

            return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, type);
                }
            }, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setBill(resultSet.getString("billDescription"));
                    order.setBillAmount(resultSet.getDouble("billAmount"));
                    order.setBillAmountWithTax(resultSet.getDouble("billAmountWithTax"));
                    order.setOrderDate(resultSet.getString("orderDate"));
                    order.setUserName(resultSet.getString("userName"));
                    order.setRoomId(resultSet.getString("roomId"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setOrderStatus(resultSet.getString("orderStatus"));

                    return order;

                }
            });
        }



    }
    //add item either a restaurant item or a dryCleaning based on employee permission
    @Override
    public void addItem(final Item item) {

        String INSERT = "INSERT INTO Items(itemDescription,itemPrice,itemType) VALUES (?,?,?);";

        jdbcTemplate.update(INSERT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, item.getDescription());
                preparedStatement.setDouble(2, item.getPrice());
                preparedStatement.setString(3, item.getType());
            }
        });

    }
    //deletes items from Db for Employee
    @Override
    public void deleteItem(final int id) {
        String DELETE = "DELETE FROM Items WHERE itemId = ? ";

        jdbcTemplate.update(DELETE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        });

    }
    //view menu of items for user based on type (restaurant or dryCleaning)
    @Override
    public List<Item> viewMenu(final String type) {

        String SELECT = "SELECT * FROM Items WHERE itemType = ?";

        return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, type);
            }
        }, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet resultSet, int i) throws SQLException {
                Item item = new Item();
                item.setId(resultSet.getInt("itemId"));
                item.setDescription(resultSet.getString("itemDescription"));
                item.setPrice(resultSet.getDouble("itemPrice"));
                return item;
            }
        });
    }
    //sets the order status to delivered by employee
    @Override
    public void deliverOrder(final int id) {
        String UPDATE = "UPDATE orders SET orderStatus = 'delivered' WHERE orderId = ? ";

        jdbcTemplate.update(UPDATE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        });
    }
    //a user can view his orders
    @Override
    public List<Order> viewUserOrders(final int id) {
        String SELECT = "SELECT * FROM orders WHERE userId = ? ORDER BY orderId DESC";

        return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        }, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setOrderDate(resultSet.getString("orderDate"));
                order.setUserName(resultSet.getString("userName"));
                order.setOrderType(resultSet.getString("orderType"));
                order.setBill(resultSet.getString("billDescription"));
                order.setBillAmountWithTax(resultSet.getDouble("billAmountWithTax"));
                order.setOrderStatus(resultSet.getString("orderStatus"));
                return order;
            }
        });
    }

    //calculates the amount of the bill
    public static List<Double> calculateBill(List<Item> items) {
        double totalSum = 0;

        double totalSumWithTax;

        ArrayList<Double> sums = new ArrayList<>();

        Item item;

        int quantity;

        for (int i = 0; i < items.size(); i++) {

            item = items.get(i);
            quantity = item.getQuantity();


            totalSum += (item.getPrice()) * quantity;

        }

        totalSumWithTax = totalSum * (1 + 0.16);

        sums.add(totalSum);
        sums.add(totalSumWithTax);

        return sums;

    }
    //generates the String receipt
    public static String generateBillDescription(List<Item> items) {
        String description = "Item\tQuantity\tPrice\n";

        Item item;

        int quantity;
        List<Double> list = calculateBill(items);

        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            quantity = item.getQuantity();
            description += item.getDescription() + "\t" + quantity + "\t" + item.getPrice() + "\n";
        }

        description += "total = " + list.get(0) + "\n" +
                "total with added tax " + list.get(1);


        return description;
    }
    //get a list of items by they're Id's
    public List<Item> getItemsById(final int id) {

        String SELECT = "SELECT * FROM Items WHERE itemId = ?";


        return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {

                preparedStatement.setInt(1, id);
            }
        }, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet resultSet, int i) throws SQLException {
                Item item = new Item();
                item.setId(resultSet.getInt("itemId"));
                item.setDescription(resultSet.getString("itemDescription"));
                item.setPrice(resultSet.getDouble("itemPrice"));
                return item;
            }
        });
    }
}
