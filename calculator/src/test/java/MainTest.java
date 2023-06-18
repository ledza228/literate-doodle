import by.ledza.Main;
import org.junit.jupiter.api.Test;


public class MainTest {

    @Test
    public void should_not_crash(){
        Main.main(new String[]{"((((toRubles($22 + $12 + toDollars(24р - 55р)) - toRubles(toDollars(22р)) + 33р + 15р))))"});
    }


}
