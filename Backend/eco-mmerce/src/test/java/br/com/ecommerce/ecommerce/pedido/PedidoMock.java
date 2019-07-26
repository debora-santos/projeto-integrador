package br.com.ecommerce.ecommerce.pedido;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.Locale;

public class PedidoMock {

    public static Pedido getPedidoMock() {
        Pedido pedido = new Pedido();
        Faker faker = new Faker(new Locale("pt-BR"));
        pedido.setNumeroPedido(faker.numerify("##########"));
        pedido.setPreco(BigDecimal.valueOf(faker.number().numberBetween(1,300)));
        pedido.setFrete(BigDecimal.valueOf(faker.number().numberBetween(1,300)));
        return pedido;
    }

}
