package buisnessLayer;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.ejb.Singleton;

@Singleton
public class main {
    public static void main(String[] args) {
//      static {
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
    }
}
