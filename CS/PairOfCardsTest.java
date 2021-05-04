package CS;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * The type Pair of cards test.
 */
public class PairOfCardsTest {
    /**
     * Winner pair of cards test.
     */
    @Test
    public void winnerPairOfCardsTest() {
        String expected = "player1";
        String[] testPlayer1 = {"♣4", "♥7", "♥7", "♠Q", "♣J"};
        String[] testPlayer2 = {"♥10", "♥6", "♣K", "♠Q", "♦2"};
        String actual = PairOfCards.winnerPairOfCards(testPlayer1, testPlayer2);
        assertThat(actual, equalTo(expected));

        String[] testPlayer3 = {"♣A","♥A","♥2","♠3","♣4"};
        String[] testPlayer4 = {"♥6","♥6","♣Q","♠Q","♦8"};
        actual = PairOfCards.winnerPairOfCards(testPlayer3, testPlayer4);
        assertThat(actual, equalTo(expected));

        String[] testPlayer5 = {"♣A","♥A","♥A","♠3","♣4"};
        String[] testPlayer6 = {"♥6","♥6","♣Q","♠Q","♦Q"};
        actual = PairOfCards.winnerPairOfCards(testPlayer5, testPlayer6);
        assertThat(actual, equalTo(expected));

        String[] testPlayer7 = {"♣K","♥K","♥K","♠A","♣A"};
        String[] testPlayer8 = {"♥6","♥6","♣Q","♠Q","♦Q"};
        actual = PairOfCards.winnerPairOfCards(testPlayer7, testPlayer8);
        assertThat(actual, equalTo(expected));

        String[] testPlayer9 = {"♥3","♠9","♦3","♣Q","♦3"};
        String[] testPlayer10 = {"♥4","♥A","♠10","♦A","♥4"};
        actual = PairOfCards.winnerPairOfCards(testPlayer9, testPlayer10);
        assertThat(actual, equalTo(expected));

        String[] testPlayer11 = {"♥9","♠8","♦7","♣6","♦5"};
        String[] testPlayer12 = {"♥9","♥8","♠7","♦6","♥4"};
        actual = PairOfCards.winnerPairOfCards(testPlayer11, testPlayer12);
        assertThat(actual, equalTo(expected));

        String[] testPlayer13= {"♥3","♠4","♦5","♣6","♦7"};
        String[] testPlayer14 = {"♥2","♥3","♠5","♦6","♥7"};
        actual = PairOfCards.winnerPairOfCards(testPlayer13, testPlayer14);
        assertThat(actual, equalTo(expected));

        String[] testPlayer15 = {"♥2","♠2","♦2","♣2","♦6","♠3","♦3","♣4","♦3"};
        String[] testPlayer16 = {"♥2","♥2","♠2","♦3","♥7","♠2","♦3","♣6","♦6"};
        actual = PairOfCards.winnerPairOfCards(testPlayer15, testPlayer16);
        assertThat(actual, equalTo(expected));

        String[] testPlayer17 = {"♥2","♠2","♦6"};
        String[] testPlayer18 = {"♥2","♥2","♥3"};
        actual = PairOfCards.winnerPairOfCards(testPlayer17, testPlayer18);
        assertThat(actual, equalTo(expected));

        expected = "player2";
        String[] testPlayer19 = {"♣4","♥7","♥7","♠Q","♣J"};
        String[] testPlayer20 = {"♥7","♥7","♣K","♠Q","♦2"};
        actual = PairOfCards.winnerPairOfCards(testPlayer19, testPlayer20);

        assertThat(actual, equalTo(expected));
        String[] testPlayer21 = {"♣9","♥8","♥7","♠4","♣5"};
        String[] testPlayer22 = {"♥10","♥8","♣7","♠4","♦5"};
        actual = PairOfCards.winnerPairOfCards(testPlayer21, testPlayer22);
        assertThat(actual, equalTo(expected));

        String[] testPlayer23 = {"♣6","♠3","♠J","♦2","♥3"};
        String[] testPlayer24 = {"♠8","♥2","♦8","♥9","♦J"};
        actual = PairOfCards.winnerPairOfCards(testPlayer23, testPlayer24);
        assertThat(actual, equalTo(expected));

        String[] testPlayer25 = {"♥3","♠9","♦3","♣Q","♦A"};
        String[] testPlayer26 = {"♥4","♥3","♠10","♦3","♥4"};
        actual = PairOfCards.winnerPairOfCards(testPlayer25, testPlayer26);
        assertThat(actual, equalTo(expected));

        String[] testPlayer27 = {"♣6","♠3","♠J","♦2","♥3"};
        String[] testPlayer28 = {"♠8","♥2","♦8","♥9","♦J"};
        actual = PairOfCards.winnerPairOfCards(testPlayer27, testPlayer28);
        assertThat(actual, equalTo(expected));

        String[] testPlayer29 = {"♥K","♥4","♣K","♠4","♦K"};
        String[] testPlayer30 = {"♣K","♥8","♥K","♠K","♣A"};
        actual = PairOfCards.winnerPairOfCards(testPlayer29, testPlayer30);
        assertThat(actual, equalTo(expected));

        String[] testPlayer31 = {"♥K","♠4","♦K","♣6","♦6"};
        String[] testPlayer32 = {"♥6","♥K","♠K","♦6","♥7"};
        actual = PairOfCards.winnerPairOfCards(testPlayer31, testPlayer32);
        assertThat(actual, equalTo(expected));

        String[] testPlayer33 = {"♥2","♠2","♦2","♣2","♦6"};
        String[] testPlayer34 = {"♥2","♥2","♠2","♦2","♥7"};
        actual = PairOfCards.winnerPairOfCards(testPlayer33, testPlayer34);
        assertThat(actual, equalTo(expected));

        String[] testPlayer35 = {"♥2","♠2","♦2","♣2","♦6","♠3","♦3","♣4","♦6"};
        String[] testPlayer36 = {"♥2","♥2","♠2","♦3","♥7","♠2","♦3","♣6","♦6"};
        actual = PairOfCards.winnerPairOfCards(testPlayer35, testPlayer36);
        assertThat(actual, equalTo(expected));

        String[] testPlayer37 = {"♥2","♠A","♦6"};
        String[] testPlayer38 = {"♥2","♥2","♥3"};
        actual = PairOfCards.winnerPairOfCards(testPlayer37, testPlayer38);
        assertThat(actual, equalTo(expected));

        expected = "draw";
        String[] testPlayer39 = {"♣A","♥2","♥3","♠4","♣5"};
        String[] testPlayer40 = {"♥A","♥2","♣3","♠4","♦5"};
        actual = PairOfCards.winnerPairOfCards(testPlayer39, testPlayer40);
        assertThat(actual, equalTo(expected));

        String[] testPlayer41 = {"♣A","♥A","♥A","♠4","♣5"};
        String[] testPlayer42 = {"♣A","♥A","♥A","♠4","♣5"};
        actual = PairOfCards.winnerPairOfCards(testPlayer41, testPlayer42);
        assertThat(actual, equalTo(expected));
    }
}