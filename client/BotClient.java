package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random()*100);
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String[] mess = message.split(":");
                String name = mess[0];
                String data = mess[1].trim();
                String infoFor = "Информация для " + name + ": ";
                String formatData = null;


                if (data.equals("дата")) {
                    formatData = new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("день")) {
                    formatData = new SimpleDateFormat("d").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("месяц")) {
                    formatData = new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("год")) {
                    formatData = new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("время")) {
                    formatData = new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("час")) {
                    formatData = new SimpleDateFormat("H").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("минуты")) {
                    formatData = new SimpleDateFormat("m").format(Calendar.getInstance().getTime());
                } else
                if (data.equals("секунды")) {
                    formatData = new SimpleDateFormat("s").format(Calendar.getInstance().getTime());
                }
                if (formatData != null) {
                        sendTextMessage(infoFor + formatData);
                }
            }



        }
    }
}
