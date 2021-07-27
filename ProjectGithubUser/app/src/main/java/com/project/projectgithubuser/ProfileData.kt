package com.project.projectgithubuser

object ProfileData {
    private val profileName = arrayOf(
        "Marjuki",
        "Suparman",
        "Tejo Sutejo",
        "Joko bin Jaka",
        "Sukijem Nawar Mulan",
        "Joshua Tejo",
        "Sarah Diskoni Sojo",
        "Bejo Muluna",
        "Spuri derman wan",
        "Batmani Argumeni"
    )

    private val profileFoto = arrayOf(
        R.drawable.mj,
        R.drawable.sp,
        R.drawable.tj,
        R.drawable.jb,
        R.drawable.sn,
        R.drawable.jt,
        R.drawable.sr,
        R.drawable.bj,
        R.drawable.sd,
        R.drawable.bt
    )

    private val profileBio = arrayOf(
            "mrjk_1",
            "suparsuperman",
            "tejostj",
            "jbjaka",
            "sukinamu",
            "jotejo",
            "pencaridiskon",
            "bejoml",
            "suprispiderman",
            "batmanarm"
    )

    private val profilePekerjaan = arrayOf(
            "Android Developer",
            "Front-end Web Developer",
            "Software Engineer",
            "Back-end Developer",
            "Machine Learning Enthusiast",
            "Android Enthusiast",
            "Cloud Engineer",
            "Data Analyst",
            "AI Engineer",
            "Full-Stack Developer"
    )

    private val profileFollowers = arrayOf(
            "10",
            "9",
            "8",
            "9",
            "10",
            "5",
            "3",
            "8",
            "3",
            "5"
    )

    private val profileFollowing = arrayOf(
            "5",
            "3",
            "8",
            "3",
            "5",
            "10",
            "8",
            "8",
            "9",
            "10"
    )

    private val tempatKerja = arrayOf(
        "Ruang Belajar Indonesia",
        "Grob Express Indonesia",
        "Ojek Food, Ojek Tech Indonesia",
        "Shoping Indonesia",
        "Universitas Indonesia",
        "Universitas Indonesia",
        "Google Cloud Indonesia",
        "Tokopedia Saja",
        "Google Indonesia",
        "Trapelokain Saja"
    )

    private val alamat =  arrayOf(
        "Jln. Impian Jaya Ancol, Jakarta Utara, DKI Jakarta 12345",
        "Jln. Ngayal Mulu, Kota Ngayal, Kabupaten Ngimpi, Imajinasiin Aja 13579",
        "Jln. Kuta, Bali, Indonesia, 11223",
        "Jln. Diponegoro, Provisi Yogyakarta, Daerah Istimewa Yogyakarta, Indonesia",
        "Jln. Ngabli, Kabu[aten Nyasar, Hilang, 12334",
        "Jln. Antasari, Jakasarta Selatan, Jakarta, Indonesia",
        "Jln. Maliboboro, Semarang, Semarang Kota, Jawa Tengah, Indonesia",
        "Jln. Pinang Raya, Pondok Labu, Cilandak, Jakarta Selatan, Jakarta, Indonesia",
        "Jln. Limo Raya, Limo, Depok, Jawa Barat, Indonesia",
        "Jln. Ambyar, Kabupaten Hambar, Kota gambar, 12344"
    )

    val listData : ArrayList<Profile>
        get() {
            val list = arrayListOf<Profile>()
            for (position in profileName.indices) {
                val profile = Profile()
                profile.name = profileName[position]
                profile.detail = profileBio[position]
                profile.photo = profileFoto[position]
                profile.pekerjaan = profilePekerjaan[position]
                profile.followers = profileFollowers[position]
                profile.following = profileFollowing[position]
                profile.tempatkerja = tempatKerja[position]
                profile.lokasi = alamat[position]
                list.add(profile)
            }
            return list
        }



}