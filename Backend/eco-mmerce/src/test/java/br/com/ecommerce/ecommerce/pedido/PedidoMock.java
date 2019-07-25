package br.com.ecommerce.ecommerce.pedido;

import com.github.javafaker.Faker;
import java.util.Locale;

public class PedidoMock {

    public static Pedido getPedidoMock() {
        Pedido pedido = new Pedido();
        Faker faker = new Faker(new Locale("pt-BR"));
        pedido.setNumeroPedido(faker.idNumber());
        pedido.setPreco(faker.internet().emailAddress());
        pedido.setFrete(faker.name().username());
        return pedido;
    }

}
