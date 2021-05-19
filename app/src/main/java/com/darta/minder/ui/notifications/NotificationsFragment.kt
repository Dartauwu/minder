package com.darta.minder.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.darta.minder.LoginActivity
import com.darta.minder.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    companion object{ val INTENT_PARCELABLE = "OBJECT_INTENT" }
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        val imageList = listOf<Image>(
            Image(R.drawable.item1),
            Image(R.drawable.item2),
            Image(R.drawable.item3),
            Image(R.drawable.item4),
            Image(R.drawable.item6),
            Image(R.drawable.item7),
            Image(R.drawable.item8),
            Image(R.drawable.item9),
            Image(R.drawable.item91),
            Image(R.drawable.item92),
            Image(R.drawable.item93),
            Image(R.drawable.item94),
            Image(R.drawable.item96),
            Image(R.drawable.item97),
            Image(R.drawable.item98),
            Image(R.drawable.item99),
            Image(R.drawable.item991),
            Image(R.drawable.item992))
        val recyclerView = root.findViewById<RecyclerView>(R.id._imageRecyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = ImageAdapter(requireActivity(), imageList) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
        auth = FirebaseAuth.getInstance()
        val button: Button = root.findViewById(R.id.button)
        button.setOnClickListener {
            auth.signOut()
            Intent(activity, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
        return root
    }
}