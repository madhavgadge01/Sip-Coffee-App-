package com.example.sip.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sip.R

data class ProductData(
    val id: Int,
    val imageRes: Int,
    val name: String,
    val description: String,
    val price: String
)
data class DtailsProductData(
    val id: Int,
    val imageRes: Int,
    val name: String,
    val caption: String,
    val description: String,
    val price: String,
    val rating: String
)


@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screenFirst") {
        composable("screenFirst") { FirstScreen(navController) }
        composable("SecondScreen") { SecondScreen(navController) }
        composable("screenThird") { ScreenThird(navController) }
        composable("Screenfroth") { Screenfroth(navController) }
        composable("Screenfive") { Screenfive(navController) }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt()
            LastScreen(productId = productId)
        }
    }
}

@Composable
fun FirstScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF38241D))
    ){
        Image(
            painter = painterResource(R.drawable.image13),
            contentDescription = "Splashscreen",
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            "Time for a coffee break....",
            modifier = Modifier
                .padding(18.5.dp)
                .fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = colorResource(R.color.white),
            textAlign = TextAlign.Center
        )

        Text(
            "Your daily dose of fresh brew delivered to your doorstep. Start your coffee journey now!",
            modifier = Modifier
                .padding(17.5.dp)
                .fillMaxWidth(),
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = colorResource(R.color.white),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .padding(top = 90.dp)
        ){
            Button(
                onClick = { navController.navigate("SecondScreen") },
                modifier = Modifier.height(50.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.orenge))
            ) {
                Text(
                    "Get Started",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Scaffold (
        bottomBar = { CustomBottom(navController) }
    ){
        MainScreen(it, navController)
    }
}

@Composable
fun MainScreen(values: PaddingValues, navController: NavHostController) {
    val productList = listOf(
        ProductData(1, R.drawable.classisc, "Classic ", "with chocolate", "₹135"),
        ProductData(2, R.drawable.dry, "Dry", "strong & bold", "₹120"),
        ProductData(3, R.drawable.wet, "Wet ", "with milk", "₹150"),
        ProductData(4, R.drawable.flavored, "Flavored", "choco delight", "₹160"),
        ProductData(5, R.drawable.iced, "Iced ", "black coffee", "₹110"),
        ProductData(6, R.drawable.cinnamon, "Cinnamon ", "smooth taste", "₹140"),
        ProductData(7, R.drawable.chocolate, "Chocolate ", "smooth taste", "₹140")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(values),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Column { CustomTop() }
        }

        items(productList) { product ->
            Product(product, navController)
        }
    }
}

@Composable
fun Product(product: ProductData, navController: NavHostController) {
    Column(
        modifier = Modifier
            .size(height = 239.dp, width = 149.dp)
            .clickable { navController.navigate("productDetail/${product.id}") },
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(height = 128.dp, width = 152.dp)
                .padding(start = 20.dp, top = 5.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Text(
            product.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 19.5.dp),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )
        Text(
            product.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color(0xff9B9B9B)
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 19.dp, end = 26.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                product.price,
                modifier = Modifier.padding(top = 4.dp),
                fontWeight = FontWeight.SemiBold,
                fontSize = 21.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(Color(0xffC67C4E))
            ) {
                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.fillMaxSize(), tint = Color.White)
            }

        }
    }
}

@Composable
fun CustomBottom(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "SecondScreen",
            onClick = { navController.navigate("SecondScreen") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xffC67C4E),
                unselectedIconColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = currentRoute == "screenThird",
            onClick = { navController.navigate("screenThird") },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "cart") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xffC67C4E),
                unselectedIconColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = currentRoute == "Screenfroth",
            onClick = { navController.navigate("Screenfroth") },
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xffC67C4E),
                unselectedIconColor = Color.Gray
            )
        )

        NavigationBarItem(
            selected = currentRoute == "Screenfive",
            onClick = { navController.navigate("Screenfive") },
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Account") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xffC67C4E),
                unselectedIconColor = Color.Gray
            )
        )
    }
}

@Composable
fun CustomTop() {
    val gradient = Brush.verticalGradient(colors = listOf(Color(0xff131313), Color(0xe0313131)))
    Column {
        Box (
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .background(gradient)
        ) {
            Column {
                Text(
                    "Location",
                    modifier = Modifier.padding(top = 50.dp, start = 20.dp),
                    fontSize = 16.sp,
                    color = Color(0xffB7B7B7),
                    fontWeight = FontWeight.Normal
                )
                Row(modifier = Modifier.padding(top = 10.dp, start = 20.dp)) {
                    Text(
                        "Sagar ,Madhya Pradesh",
                        fontSize = 22.sp,
                        color = Color(0xffffffff),
                        fontWeight = FontWeight.ExtraBold
                    )

                    Icon(Icons.Filled.KeyboardArrowDown, modifier = Modifier.size(19.dp), contentDescription = null, tint = Color.White)
                }
            }
        }
        Box(modifier = Modifier
            .padding(horizontal = 20.dp)
            .offset(y = -30.dp)) { SearchBr() }
        HorizontalSlider()
    }
}

@Composable
fun HorizontalSlider() {
    val items = listOf("Cappucinno", "Machiato", "Latte", "Liberica", "Flat White")
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items.forEachIndexed { index, data ->
            SliderItems(index == 0, data)
        }
    }
}

@Composable
fun SliderItems(isSelected: Boolean, text: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .size(width = 121.dp, height = 38.dp)
            .shadow(8.dp, RoundedCornerShape(9.dp))
            .clip(RoundedCornerShape(9.dp))
            .background(if (isSelected) Color(0xffC67C4E) else Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (isSelected) Color.White else Color(0xff2F4B4E)
        )
    }
}

@Composable
fun SearchBr() {
    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .height(70.dp)
            .shadow(12.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(15.dp))
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Filled.Search, modifier = Modifier.size(30.dp), contentDescription = null)

        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            singleLine = true,
            modifier = Modifier
                .padding(start = 19.dp)
                .weight(1f),
            decorationBox = { innerTextField ->
                if (searchText.isEmpty()) {
                    Text("Search Coffee", fontSize = 20.sp, color = Color(0xff989898))
                }
                innerTextField()
            }
        )

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xffC67C4E))
        ) {
            Icon(Icons.Filled.List, contentDescription = null, modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), tint = Color.White)
        }
    }
}

@Composable
fun ScreenThird(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CustomBottom(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(R.drawable.cart2),
                contentDescription = null, contentScale = ContentScale.FillBounds)
        }
    }
}

@Composable
fun Screenfroth(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CustomBottom(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(R.drawable.whitenoti), contentDescription = null)
        }
    }
}

@Composable
fun Screenfive(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { CustomBottom(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("This is Fifth Screen")
        }
    }
}

@Composable
fun LastScreen(productId: Int?) {
    val DtailsProductData = listOf(
        DtailsProductData(1, R.drawable.classisc, "Classic ", "with chocolate","The Classic Cappuccino is a timeless blend of espresso, steamed milk, and a rich foam layer.\n" +
                "It delivers a strong coffee flavor balanced perfectly with creamy smoothness.\n" +
                "This is the most traditional style enjoyed by coffee lovers around the world.\n" +
                "A perfect choice to kick-start your day with freshness and energy.", "₹ 135","4.0"),


        DtailsProductData(2, R.drawable.dry, "Dry", "strong & bold", "The Dry Cappuccino contains more foam and less steamed milk, making the flavor bold and strong.\n" +
                "Its thick and airy foam highlights the true essence of espresso.\n" +
                "This coffee is less creamy but has an intense and authentic taste.\n" +
                "Perfect for those who love strong and powerful coffee notes.","₹ 120","3.3"),


        DtailsProductData(3, R.drawable.wet, "Wet ", "with milk", "The Wet Cappuccino has more steamed milk and less foam compared to the classic.\n" +
                "It creates a smooth, creamy, and rich texture with a mild flavor.\n" +
                "The taste is softer, making it ideal for those who prefer less bitterness.\n" +
                "It is often considered a bridge between a cappuccino and a latte.","₹ 150","3.9"),


        DtailsProductData(4, R.drawable.flavored, "Flavored", "choco delight", "The Flavored Cappuccino comes with a twist of syrups like vanilla, hazelnut, caramel, or chocolate.\n" +
                "It blends the richness of espresso with exciting sweet notes.\n" +
                "Every sip gives a unique flavor while keeping the essence of coffee alive.\n" +
                "Perfect for coffee lovers who enjoy experimenting with tastes","₹ 160","2.2"),


        DtailsProductData(5, R.drawable.iced, "Iced ", "black coffee", "The Iced Cappuccino is a refreshing cold version, perfect for hot summer days.\n" +
                "It is prepared with espresso, cold milk, ice, and topped with light foam.\n" +
                "The taste is cool, smooth, and frothy, giving a refreshing experience.\n" +
                "Best suited for those who want their coffee chilled and energizing.","₹ 110","5.0"),

        DtailsProductData(6, R.drawable.cinnamon, "Cinnamon ", "smooth taste", "The Cinnamon Cappuccino is enriched with a sprinkle of cinnamon powder or syrup.\n" +
                "It has a warm, aromatic, and slightly spicy taste that blends beautifully with espresso.\n" +
                "The cinnamon aroma adds coziness and comfort to every sip.\n" +
                "A popular choice during winters for its rich and soothing flavor.","₹ 140","4.8"),

        DtailsProductData(7, R.drawable.chocolate,"Chocolate ", "smooth taste", "The Chocolate Cappuccino combines espresso and milk with chocolate syrup or cocoa powder.\n" +
                "It delivers a sweet, indulgent, and dessert-like coffee experience.\n" +
                "The texture is creamy and rich, making it a favorite among chocolate lovers.\n" +
                "Perfect for those who like their coffee with a sweet and chocolaty twist.","₹ 140","3.9")
    )
    val product = DtailsProductData.find { it.id == productId }

    Box(modifier = Modifier.fillMaxSize()) {
        if (product != null) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp)
            ) {
                Image(
                    painter = painterResource(product.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }


            Row(
                modifier = Modifier.padding(top = 5.dp, start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    product.name,
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier.size(29.dp),
                    tint = Color(0xffE27D19)
                )
                Text(
                    product.rating.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }


            Text(
                product.caption,
                modifier = Modifier.padding(top = 5.dp, start = 15.dp, bottom = 5.dp),
                fontSize = 16.sp
            )

            Divider(modifier = Modifier.height(1.dp))


            Text(
                "Description",
                modifier = Modifier.padding(start = 15.dp, top = 5.dp),
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold
            )


            Box(modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp)) {
                Text(product.description,
                    fontSize = 20.sp
                )
            }
        }


        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(90.dp)
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    "Price",
                    fontSize = 19.sp,
                    color = Color(0xff9B9B9B)
                )
                Text(
                    product.price,
                    fontSize = 22.sp,
                    color = Color(0xffE27D19),
                    fontWeight = FontWeight.SemiBold
                )
            }


            Box(
                modifier = Modifier
                    .size(width = 151.dp, height = 48.dp)
                    .shadow(11.dp, RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFFC67C4E)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Buy Now",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }}
}


