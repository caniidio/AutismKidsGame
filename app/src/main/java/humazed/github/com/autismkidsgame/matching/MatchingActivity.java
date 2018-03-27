package humazed.github.com.autismkidsgame.matching;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bakrabros.android.puzzle.channel.Channel;
import com.bakrabros.android.puzzle.generator.ThreeSlotPuzzleGenerator;
import com.bakrabros.android.puzzle.manager.ThreeSlotPuzzleManagerImpl;
import com.bakrabros.android.puzzle.model.Puzzle;
import com.bakrabros.android.puzzle.util.java8.Consumer;
import com.bakrabros.android.puzzle.view.PuzzleView;

import humazed.github.com.autismkidsgame.R;

import static com.bakrabros.android.puzzle.constant.PuzzleEvents.PUZZLE_COMPLETED;
import static com.bakrabros.android.puzzle.constant.PuzzleEvents.SHOW_NEXT_PUZZLE_PIECE;

public class MatchingActivity extends AppCompatActivity {

    /**
     * The puzzle view in this activity's view
     */
    private PuzzleView puzzleView;

    private Channel channel;

    private ThreeSlotPuzzleGenerator threeSlotPuzzleGenerator;

    private Puzzle currentPuzzle;

    private ThreeSlotPuzzleManagerImpl puzzleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        puzzleView = findViewById(R.id.puzzleView);

        channel = new Channel();

        puzzleView.post(new Runnable() {
            @Override
            public void run() {
                threeSlotPuzzleGenerator = new ThreeSlotPuzzleGenerator(
                        getApplicationContext(),
                        "data.json",
                        puzzleView.getWidth(),
                        puzzleView.getHeight());

                startNextPuzzleGame();
            }
        });

        channel.subscribe(PUZZLE_COMPLETED, new Consumer<View>() {
            @Override
            public void accept(View view) {
                new AlertDialog.Builder(MatchingActivity.this)
                        .setTitle("Puzzle Completed!!")
//                        .setMessage("Replay?")
//                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                startNextPuzzleGame();
                            }
                        }).show();
            }
        });
    }

    private void startNextPuzzleGame() {
        currentPuzzle = threeSlotPuzzleGenerator.generateNewPuzzle();
        puzzleManager = new ThreeSlotPuzzleManagerImpl(channel, currentPuzzle, getResources().getInteger(R.integer.snap_distance));
        puzzleView.setPuzzleManager(puzzleManager);
        channel.emit(SHOW_NEXT_PUZZLE_PIECE);
        puzzleView.invalidate();
    }
}
