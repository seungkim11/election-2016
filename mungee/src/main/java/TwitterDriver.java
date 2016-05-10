import acquisition.TwitterSource;

/**
 * Created by samskim on 5/8/16.
 */
public class TwitterDriver {

    public static void main(String[] args){


        TwitterSource source = new TwitterSource();
        String[] track = {"2016 election", "Bernie Sanders", "Hilary Clinton", "Donald Trump", "Ted Cruz"};
        source.listen(track, true);

    }


}
