package com.pslima.mancala;

import com.pslima.mancala.enums.Player;
import com.pslima.mancala.service.BoardService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class BoardServiceTests {

    @Autowired
    BoardService boardService;

    @Test
    public void boardServiceMoveTo() {
        List<Integer> initial = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);
        List<Integer> expected = Arrays.asList(0, 5, 5, 5, 5, 4, 0, 4, 4, 4, 4, 4, 4, 0);

        boardService.setCurrentBoard(initial);
        boardService.moveFrom(0, Player.PLAYER_1);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void boardServiceMoveToShouldFailDuePlayer1OutOfBounds() {
        List<Integer> initial = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);
        List<Integer> expected = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);

        boardService.setCurrentBoard(initial);
        boolean hasMoved = boardService.moveFrom(6, Player.PLAYER_1);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertFalse(hasMoved);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void boardServiceMoveToShouldFailDuePlayer2OutOfBounds() {
        List<Integer> initial = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);
        List<Integer> expected = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);

        boardService.setCurrentBoard(initial);
        boolean hasMoved = boardService.moveFrom(1, Player.PLAYER_2);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertFalse(hasMoved);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void boardServiceMoveToShouldP1SkipP2Mancala() {
        List<Integer> initial = Arrays.asList(0, 0, 0, 0, 0, 24, 0, 4, 4, 4, 4, 4, 4, 0);
        List<Integer> expected = Arrays.asList(2, 2, 2, 2, 1, 1, 2, 6, 6, 6, 6, 6, 6, 0);

        boardService.setCurrentBoard(initial);
        boolean hasMoved = boardService.moveFrom(5, Player.PLAYER_1);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertTrue(hasMoved);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void boardServiceMoveToShouldP2SkipP1Mancala() {
        List<Integer> initial = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 24, 0);
        List<Integer> expected = Arrays.asList(6, 6, 6, 6, 6, 6, 0, 2, 2, 2, 2, 1, 1, 2);

        boardService.setCurrentBoard(initial);
        boolean hasMoved = boardService.moveFrom(12, Player.PLAYER_2);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertTrue(hasMoved);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void boardServiceMoveToShouldTakeRivalSeedsOnLastSeed() {
        List<Integer> initial = Arrays.asList(0, 0, 1, 0, 0, 0, 23, 0, 0, 4, 0, 0, 0, 20);
        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 20);

        boardService.setCurrentBoard(initial);
        boolean hasMoved = boardService.moveFrom(2, Player.PLAYER_1);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertTrue(hasMoved);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void boardServiceMoveToP2ShouldTakeRivalSeedsOnLastSeed() {
        List<Integer> initial = Arrays.asList(0, 0, 4, 0, 0, 0, 20, 0, 0, 1, 0, 0, 0, 23);
        List<Integer> expected = Arrays.asList(0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 28);

        boardService.setCurrentBoard(initial);
        boolean hasMoved = boardService.moveFrom(9, Player.PLAYER_2);
        List<Integer> actual = boardService.getCurrentBoard();

        Assert.assertTrue(hasMoved);
        Assert.assertEquals(expected, actual);
    }

    @TestConfiguration
    static class BoardServiceTestConfiguration {
        @Bean
        public BoardService boardService() {
            return new BoardService();
        }
    }
}
