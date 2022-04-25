package com.payconiq.app.ui.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.payconiq.app.R
import com.payconiq.app.databinding.FragmentUserDetailsBinding
import com.payconiq.app.models.GithubSearchModel

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {


    private lateinit var bitmap: Bitmap
    private val args by navArgs<UserDetailsFragmentArgs>()

    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var githubSearchItem: GithubSearchModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserDetailsBinding.bind(view)
        githubSearchItem = args.user
        bindViews()
    }

    private fun bindViews() {

        with(binding)
        {
            apply {

                Glide.with(this@UserDetailsFragment)
                    .load(githubSearchItem.avatarUrl)
                    .error(R.drawable.ic_error)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                            progressBar.isVisible = false
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            progressBar.isVisible = false
                            bitmap = (resource as BitmapDrawable).bitmap
                            return false
                        }
                    })
                    .into(binding.imageView)
            }
        }
        binding.data = githubSearchItem
    }

}