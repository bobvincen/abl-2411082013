package com.habibi.order.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.habibi.order.Entity.Order;
import com.habibi.order.Repository.OrderRepository;
import com.habibi.order.vo.OrderVO;
import com.habibi.order.vo.PelangganVO;
import com.habibi.order.vo.ProdukVO;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final RestClient restClient;

    public OrderService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public OrderVO saveOrder(Order order) {
        order.setId(null);

    // Cek Pelanggan
    PelangganVO pelanggan = getPelanggan(order.getPelanggan_id());

    if (pelanggan == null) {
        throw new RuntimeException("Pelanggan tidak ditemukan");
    }

    // Cek Produk
    ProdukVO produk = getProduk(order.getProduk_id());

    if (produk == null) {
        throw new RuntimeException("Produk tidak ditemukan");
    }

    // Ambil harga Produk dan Menghtiung total
    double total = produk.getHarga() * order.getJumlah();

    order.setTotal(total);

    Order savedOrder = orderRepository.save(order);

    // Simpan Order
    return getOrderVO(savedOrder);
}

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setProduk_id(order.getProduk_id());
            existing.setPelanggan_id(order.getPelanggan_id());
            existing.setTgl_trans(order.getTgl_trans());
            existing.setJumlah(order.getJumlah());
            existing.setTotal(order.getTotal());
            return orderRepository.save(existing);
        }
        return null;
    }

    // Mengambil Data Pelanggan

    public PelangganVO getPelanggan(Long id) {

        return restClient.get()
                .uri("http://localhost:8081/api/pelanggan/" + id)
                .retrieve()
                .body(PelangganVO.class);
    }

    // Mengambil Data Produk

    public ProdukVO getProduk(Long id) {

        return restClient.get()
                .uri("http://localhost:8082/api/produk/" + id)
                .retrieve()
                .body(ProdukVO.class);
    }

    public OrderVO getOrderVO(Order order) {

        PelangganVO pelanggan = getPelanggan(order.getPelanggan_id());
        ProdukVO produk = getProduk(order.getProduk_id());

        OrderVO orderVO = new OrderVO();

        orderVO.setId(order.getId());
        orderVO.setProdukId(order.getProduk_id());
        orderVO.setPelangganId(order.getPelanggan_id());
        orderVO.setJumlah(order.getJumlah());
        orderVO.setTglTrans(order.getTgl_trans());
        orderVO.setTotal(order.getTotal());

        orderVO.setPelanggan(pelanggan);
        orderVO.setProduk(produk);

        return orderVO;
    }

}