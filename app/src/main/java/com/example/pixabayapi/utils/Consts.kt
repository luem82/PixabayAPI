package com.example.pixabayapi.utils

import com.example.pixabayapi.datas.model.PhotoDetail
import com.example.pixabayapi.datas.model.VideoDetail
import com.kodmap.app.library.loader.utils.L

object Consts {

    const val BASE_URL = "https://pixabay.com"
    const val API_KEY = "17400316-ab031a1f11b1982232b6cba56"
    const val LANG = "vi"
    const val PHOTO_TYPE_PHOTO = "photo"
    const val PHOTO_TYPE_ILLUS = "illustration"
    const val PHOTO_TYPE_VECTOR = "vector"
    const val VIDEO_TYPE_FILM = "film"
    const val VIDEO_TYPE_ANIM = "animation"

    var LIST_PHOTO_PHOTO = mutableListOf<PhotoDetail>()
    var LIST_PHOTO_ILLUS = mutableListOf<PhotoDetail>()
    var LIST_PHOTO_VECTOR = mutableListOf<PhotoDetail>()

    var LIST_VIDEO_FILM = mutableListOf<VideoDetail>()
    var LIST_VIDEO_ANIM = mutableListOf<VideoDetail>()

    val LIST_CATE_KEY = listOf(
        "backgrounds/Hình Nền",
        "fashion/Thời Trang",
        "nature/Thiên Nhiên",
        "science/Khoa Học",
        "education/Giáo Dục",
        "feelings/Cảm Xúc",
        "health/Sức Khòe",
        "people/Con Người",
        "religion/Tôn Giáo",
        "places/Địa Điểm",
        "animals/Động Vật",
        "industry/Nghành Nghề",
        "computer/Máy Tính",
        "food/Thức Ăn",
        "sports/Thể Thao",
        "transportation/Vận Chuyển",
        "travel/Du Lịch",
        "buildings/Tòa Nhà",
        "business/Kinh Doanh",
        "music/Âm Nhạc"
    )

    val LIST_CATE_THUMB = mutableMapOf(

        0 to listOf(
            "https://cdn.pixabay.com/photo/2014/09/14/18/04/dandelion-445228_150.jpg",
            "https://cdn.pixabay.com/photo/2013/08/20/15/47/poppies-174276_150.jpg",
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_150.jpg",
            "https://cdn.pixabay.com/photo/2015/12/03/08/50/paper-1074131_150.jpg"
        ),
        1 to listOf(
            "https://cdn.pixabay.com/photo/2016/06/11/12/13/pink-hair-1450045_150.jpg",
            "https://cdn.pixabay.com/photo/2015/06/22/08/40/child-817373_150.jpg",
            "https://cdn.pixabay.com/photo/2014/05/03/00/56/summerfield-336672_150.jpg",
            "https://cdn.pixabay.com/photo/2015/09/06/00/46/yellow-926728_150.jpg"
        ),
        2 to listOf(
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_150.jpg",
            "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823_150.jpg",
            "https://cdn.pixabay.com/photo/2014/02/27/16/10/tree-276014_150.jpg",
            "https://cdn.pixabay.com/photo/2015/09/09/16/05/forest-931706_150.jpg"
        ),
        3 to listOf(
            "https://cdn.pixabay.com/photo/2015/11/04/20/59/milky-way-1023340_150.jpg",
            "https://cdn.pixabay.com/photo/2015/10/12/14/59/milky-way-984050_150.jpg",
            "https://cdn.pixabay.com/photo/2011/12/13/14/31/earth-11015_150.jpg",
            "https://cdn.pixabay.com/photo/2015/07/02/10/13/sky-828648_150.jpg"
        ),
        4 to listOf(
            "https://cdn.pixabay.com/photo/2014/09/05/18/32/old-books-436498_150.jpg",
            "https://cdn.pixabay.com/photo/2015/12/19/20/32/paper-1100254_150.jpg",
            "https://cdn.pixabay.com/photo/2015/12/01/15/43/black-1072366_150.jpg",
            "https://cdn.pixabay.com/photo/2015/09/02/12/28/pencil-918449_150.jpg"
        ),
        5 to listOf(
            "https://cdn.pixabay.com/photo/2018/02/12/10/45/heart-3147976_150.jpg",
            "https://cdn.pixabay.com/photo/2015/05/11/14/51/heart-762564_150.jpg",
            "https://cdn.pixabay.com/photo/2017/11/26/15/16/smiley-2979107_150.jpg",
            "https://cdn.pixabay.com/photo/2017/11/06/23/19/composing-2925179_150.jpg"
        ),
        6 to listOf(
            "https://cdn.pixabay.com/photo/2016/04/13/22/12/hands-1327811_150.jpg",
            "https://cdn.pixabay.com/photo/2017/05/25/15/08/jogging-2343558_150.jpg",
            "https://cdn.pixabay.com/photo/2018/02/06/14/07/dance-3134828_150.jpg",
            "https://cdn.pixabay.com/photo/2018/05/26/10/54/strawberries-3431122_150.jpg"
        ),
        7 to listOf(
            "https://cdn.pixabay.com/photo/2015/03/17/14/05/sparkler-677774_150.jpg",
            "https://cdn.pixabay.com/photo/2016/03/09/09/22/workplace-1245776_150.jpg",
            "https://cdn.pixabay.com/photo/2014/12/16/22/25/sunset-570881_150.jpg",
            "https://cdn.pixabay.com/photo/2014/09/07/21/52/city-438393_150.jpg"
        ),
        8 to listOf(
            "https://cdn.pixabay.com/photo/2020/05/05/23/14/statue-5135429_150.jpg",
            "https://cdn.pixabay.com/photo/2016/02/05/19/51/stained-glass-1181864_150.jpg",
            "https://cdn.pixabay.com/photo/2016/11/08/05/16/boy-1807518_150.jpg",
            "https://cdn.pixabay.com/photo/2016/11/03/04/02/boys-1793421_150.jpg"
        ),
        9 to listOf(
            "https://cdn.pixabay.com/photo/2014/05/02/21/50/laptop-336378_150.jpg",
            "https://cdn.pixabay.com/photo/2016/03/26/13/09/notebook-1280538_150.jpg",
            "https://cdn.pixabay.com/photo/2016/02/10/00/07/bench-1190768_150.jpg",
            "https://cdn.pixabay.com/photo/2018/03/10/17/16/woman-3214594_150.jpg"
        ),
        10 to listOf(
            "https://cdn.pixabay.com/photo/2012/06/19/10/32/owl-50267_150.jpg",
            "https://cdn.pixabay.com/photo/2017/01/14/12/59/iceland-1979445_150.jpg",
            "https://cdn.pixabay.com/photo/2018/08/12/16/59/ara-3601194_150.jpg",
            "https://cdn.pixabay.com/photo/2017/05/31/18/38/sea-2361247_150.jpg"
        ),
        11 to listOf(
            "https://cdn.pixabay.com/photo/2016/03/04/19/36/gears-1236578_150.jpg",
            "https://cdn.pixabay.com/photo/2016/06/12/20/27/macro-1452987_150.jpg",
            "https://cdn.pixabay.com/photo/2018/08/31/19/16/fan-3645379_150.jpg",
            "https://cdn.pixabay.com/photo/2014/02/05/08/19/smoke-258786_150.jpg"
        ),
        12 to listOf(
            "https://cdn.pixabay.com/photo/2014/05/02/21/49/laptop-336373_150.jpg",
            "https://cdn.pixabay.com/photo/2015/07/17/22/43/student-849825_150.jpg",
            "https://cdn.pixabay.com/photo/2015/01/08/18/24/children-593313_150.jpg",
            "https://cdn.pixabay.com/photo/2016/03/26/13/09/cup-of-coffee-1280537_150.jpg"
        ),
        13 to listOf(
            "https://cdn.pixabay.com/photo/2010/12/13/10/05/berries-2277_150.jpg",
            "https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466_150.jpg",
            "https://cdn.pixabay.com/photo/2016/03/23/15/00/ice-cream-1274894_150.jpg",
            "https://cdn.pixabay.com/photo/2017/01/31/09/30/raspberry-2023404_150.jpg"
        ),
        14 to listOf(
            "https://cdn.pixabay.com/photo/2016/09/18/14/21/swimmer-1678307_150.jpg",
            "https://cdn.pixabay.com/photo/2017/05/25/15/08/jogging-2343558_150.jpg",
            "https://cdn.pixabay.com/photo/2018/02/06/14/07/dance-3134828_150.jpg",
            "https://cdn.pixabay.com/photo/2017/02/26/11/57/mountaineer-2100050_150.jpg"
        ),
        15 to listOf(
            "https://cdn.pixabay.com/photo/2015/08/13/17/24/vintage-1950s-887272_150.jpg",
            "https://cdn.pixabay.com/photo/2017/06/05/11/01/airport-2373727_150.jpg",
            "https://cdn.pixabay.com/photo/2012/10/10/05/04/train-60539_150.jpg",
            "https://cdn.pixabay.com/photo/2014/07/01/12/35/taxi-381233_150.jpg"
        ),
        16 to listOf(
            "https://cdn.pixabay.com/photo/2020/05/05/23/14/statue-5135429_150.jpg",
            "https://cdn.pixabay.com/photo/2015/10/30/20/13/sunrise-1014712_150.jpg",
            "https://cdn.pixabay.com/photo/2017/01/20/00/30/maldives-1993704_150.jpg",
            "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_150.jpg"
        ),
        17 to listOf(
            "https://cdn.pixabay.com/photo/2018/07/14/15/27/cafe-3537801_150.jpg",
            "https://cdn.pixabay.com/photo/2015/05/15/14/21/architecture-768432_150.jpg",
            "https://cdn.pixabay.com/photo/2015/11/27/20/28/florence-1066314_150.jpg",
            "https://cdn.pixabay.com/photo/2017/04/24/13/37/architecture-2256489_150.jpg"
        ),
        18 to listOf(
            "https://cdn.pixabay.com/photo/2016/03/09/09/22/workplace-1245776_150.jpg",
            "https://cdn.pixabay.com/photo/2016/02/19/11/19/office-1209640_150.jpg",
            "https://cdn.pixabay.com/photo/2015/02/02/11/09/office-620822_150.jpg",
            "https://cdn.pixabay.com/photo/2015/03/26/09/41/tie-690084_150.jpg"
        ),
        19 to listOf(
            "https://cdn.pixabay.com/photo/2020/05/01/06/18/speaker-5115559_150.jpg",
            "https://cdn.pixabay.com/photo/2016/02/19/11/36/microphone-1209816_150.jpg",
            "https://cdn.pixabay.com/photo/2016/09/08/21/09/piano-1655558_150.jpg",
            "https://cdn.pixabay.com/photo/2015/07/30/17/24/audience-868074_150.jpg"
        )

    )


}