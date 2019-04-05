package fr.lcsdavid.rmi.server;

import fr.lcsdavid.rmi.Foo;

public class FooImpl implements Foo {
    private int count = 0;

    @Override
    public String sayFoo(String name) {
        count++;
        if (count % 17 == 0)
            return "Вы свободны товарищ.";
        if (count % 5 == 0)
            return "Освободить собак ВОДКАAAAAAAAAAAAA!";
        return "Солдаты! Отведи " + name + " в ГУЛАГ!";
    }
}
