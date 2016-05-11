import acquisition.TwitterSource;

/**
 * Created by samskim on 5/8/16.
 */
public class TwitterAPIDriver {

    public static void main(String[] args){

        TwitterSource source = new TwitterSource();
        if (args.length > 0){
            System.out.println("Skip parameter: " + args[0]);
            source.setSkip(args[0]);
        }

        String[] track = {"2016 election", "Bernie Sanders", "Hilary Clinton", "Donald Trump", "Ted Cruz"};
        source.listen(track, true);

    }


}
