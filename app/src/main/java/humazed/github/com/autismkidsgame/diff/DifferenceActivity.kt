package humazed.github.com.autismkidsgame.diff

import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import humazed.github.com.autismkidsgame.MainActivity
import humazed.github.com.autismkidsgame.R
import kotlinx.android.synthetic.main.activity_difference.*
import org.jetbrains.anko.startActivity


class DifferenceActivity : AppCompatActivity() {

    private val ROBOTO_BOLD = "roboto_bold.ttf"
    private val ROBOTO_MEDIUM = "roboto_medium.ttf"
    private val ROBOTO_REGULAR = "roboto_regular.ttf"

    private val boldTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_BOLD) }
    private val mediumTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_MEDIUM) }
    private val regularTypeface by lazy { Typeface.createFromAsset(assets, ROBOTO_REGULAR) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difference)

        titleTextView.typeface = mediumTypeface
        subtitleTextView.typeface = boldTypeface
        hintTextView.typeface = regularTypeface
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            subtitleTextView.letterSpacing = 0.06f
            hintTextView.letterSpacing = 0.05f
        }

        val titles: Array<String> = resources.getStringArray(R.array.countries)
        val colors = resources.obtainTypedArray(R.array.colors)
        val images = resources.obtainTypedArray(R.array.images)

        picker.adapter = object : BubblePickerAdapter {
            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    typeface = mediumTypeface
                    textColor = ContextCompat.getColor(this@DifferenceActivity, android.R.color.white)
                    backgroundImage = ContextCompat.getDrawable(this@DifferenceActivity, images.getResourceId(position, 0))
                }
            }
        }

        colors.recycle()
        images.recycle()

        picker.bubbleSize = 60
        picker.listener = object : BubblePickerListener {
            override fun onBubbleDeselected(item: PickerItem) {
                if (item.title == "Ship") {
                    val mp: MediaPlayer = MediaPlayer.create(this@DifferenceActivity, R.raw.applause)
                    mp.setOnCompletionListener {
                        it.reset()
                        it.release()
                    }
                    mp.start()

                    AlertDialog.Builder(this@DifferenceActivity)
                            .setTitle("Puzzle Completed!!")
                            //                        .setMessage("Replay?")
                            //                        .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes) { _, _ -> startActivity<MainActivity>() }
                            .show()
                } else {
                    AlertDialog.Builder(this@DifferenceActivity)
                            .setTitle("Wrong!!")
                            .setMessage("Choose again")
                            //                        .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes) { dialog, which -> dialog.dismiss() }
                            .show()

                }
            }

            override fun onBubbleSelected(item: PickerItem) {
            }
        }
        /*{
            override fun onBubbleSelected(item: PickerItem) = toast("${item.title} selected")

            override fun onBubbleDeselected(item: PickerItem) = toast("${item.title} deselected")
        }*/
    }

    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }
}