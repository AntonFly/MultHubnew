package dataAccesLayer.util;

import buisnessLayer.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBService {
        private static EntityManagerFactory managerFactory;

        static {

                ApiContextInitializer.init();
                DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

//        HttpHost httpHost = new HttpHost("46.105.221.247", 1080);

//        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
//        botOptions.setRequestConfig(requestConfig);
//        botOptions.setProxyHost("46.105.221.247");
//        botOptions.setProxyPort(1080);
                Bot bot= new Bot(botOptions);
                TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
                try {
                    telegramBotsApi.registerBot(bot);
                } catch (TelegramApiRequestException e) {
                    e.printStackTrace();
                }
            managerFactory = Persistence.createEntityManagerFactory("MULTHUB");
        }

        private DBService() {}

    private static EntityManagerFactory getEntytiManagerFactory(){

            return managerFactory;
    }

    public static EntityManager getEntytiManager(){

            return getEntytiManagerFactory().createEntityManager();
    }
    public static EntityTransaction getTransaction() {
        EntityTransaction transaction = getEntytiManager().getTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }
        return transaction;
    }


}

