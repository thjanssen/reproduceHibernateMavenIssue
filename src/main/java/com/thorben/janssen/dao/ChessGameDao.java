package com.thorben.janssen.dao;

import java.util.List;

import org.hibernate.annotations.processing.Find;
import org.hibernate.annotations.processing.HQL;
import org.hibernate.annotations.processing.SQL;

import com.thorben.janssen.model.ChessGame;

public interface ChessGameDao {
    
    // Generate the required code to executed the provided statement as a HQL query
    @HQL("SELECT g FROM ChessGame g LEFT JOIN FETCH g.moves WHERE g.playerWhite = :playerWhite")
    List<ChessGame> findGamesWithMovesByPlayerWhite(String playerWhite);

    // Generate the required code to execute the provided statement as a native query
    @SQL("SELECT * FROM ChessGame g WHERE g.playerWhite = :playerWhite")
    List<ChessGame> findNativeGamesWithMovesByPlayerWhite(String playerWhite);

    // Generate a statement that returns records that match all parameters
    // Method names have no semantics.
    @Find
    List<ChessGame> justSomeRandomMethodName(String playerWhite, String playerBlack);
}
