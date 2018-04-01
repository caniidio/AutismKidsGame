package humazed.github.com.autismkidsgame.matching

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.bakrabros.android.puzzle.channel.Channel
import com.bakrabros.android.puzzle.constant.PuzzleEvents.PUZZLE_COMPLETED
import com.bakrabros.android.puzzle.constant.PuzzleEvents.SHOW_NEXT_PUZZLE_PIECE
import com.bakrabros.android.puzzle.generator.ThreeSlotPuzzleGenerator
import com.bakrabros.android.puzzle.manager.ThreeSlotPuzzleManagerImpl
import com.bakrabros.android.puzzle.model.Puzzle
import com.bakrabros.android.puzzle.view.PuzzleView
import humazed.github.com.autismkidsgame.MainActivity
import humazed.github.com.autismkidsgame.R
import org.jetbrains.anko.startActivity

class MatchingActivity : AppCompatActivity() {

    /**
     * The puzzle view in this activity's view
     */
    private var puzzleView: PuzzleView? = null

    private var channel: Channel? = null

    private var threeSlotPuzzleGenerator: ThreeSlotPuzzleGenerator? = null

    private var currentPuzzle: Puzzle? = null

    private var puzzleManager: ThreeSlotPuzzleManagerImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching)

        puzzleView = findViewById(R.id.puzzleView)

        channel = Channel()

        puzzleView!!.post {
            threeSlotPuzzleGenerator = ThreeSlotPuzzleGenerator(
                    applicationContext,
                    "data.json",
                    puzzleView!!.width,
                    puzzleView!!.height)

            startNextPuzzleGame()
        }

        channel?.subscribe(PUZZLE_COMPLETED) {
            val mp: MediaPlayer = MediaPlayer.create(this@MatchingActivity, R.raw.applause)
            mp.setOnCompletionListener {
                it.reset()
                it.release()
            }
            mp.start()

            MaterialDialog.Builder(this@MatchingActivity)
                    .title("Puzzle Completed!!")
                    .iconRes(R.drawable.clap)
                    .positiveText(android.R.string.yes)
                    .onAny { dialog, which -> startNextPuzzleGame() }
                    .show()
            //                        .setMessage("Replay?")
            //                        .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setPositiveButton(android.R.string.yes) { dialog, whichButton -> startNextPuzzleGame() }.show()
        }
    }

    private fun startNextPuzzleGame() {
        currentPuzzle = threeSlotPuzzleGenerator!!.generateNewPuzzle()
        puzzleManager = ThreeSlotPuzzleManagerImpl(channel!!, currentPuzzle!!, resources.getInteger(R.integer.snap_distance).toFloat())
        puzzleView!!.setPuzzleManager(puzzleManager!!)
        channel!!.emit(SHOW_NEXT_PUZZLE_PIECE)
        puzzleView!!.invalidate()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity<MainActivity>()
    }
}
