package com.example.videopager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.fragment_video.*

private val videoUrls = listOf<String>(
    "http://ftp.nluug.nl/pub/graphics/blender/demo/movies/Sintel.2010.720p.mkv",
    "https://media.w3.org/2010/05/sintel/trailer.mp4",
    "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
    "https://vod-progressive.akamaized.net/exp=1623771481~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2670%2F7%2F188350983%2F623685558.mp4~hmac=081a2de37f5624db987be56681774caa734cae2cb40fac4d2df16b2102a5a763/vimeo-prod-skyfire-std-us/01/2670/7/188350983/623685558.mp4?filename=Emoji+Saver+-+Patterns+in+the+Rain.mp4",
    "https://vod-progressive.akamaized.net/exp=1623771485~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2684%2F7%2F188421287%2F623661526.mp4~hmac=096c8c33d07716451aa0f61bccff835cb02ad43008f5f92625520b49101f8681/vimeo-prod-skyfire-std-us/01/2684/7/188421287/623661526.mp4?filename=Emoji+Saver+-+Speckle.mp4",
    "http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4",
    "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4",
    "https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217021133Eggh6zdlAO.mp4",
    "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4",
    "http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4",
    "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4"

)
class VideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoViewPager.apply {
            adapter = object :FragmentStateAdapter(this@VideoFragment) {
                override fun getItemCount() = videoUrls.size

                override fun createFragment(position: Int) = PlayerFragment(videoUrls[position])
            }
            offscreenPageLimit = 5
        }
    }
}