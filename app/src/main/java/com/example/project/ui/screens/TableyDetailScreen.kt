package com.example.project.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.data.model.CompatibilityStatus
import com.example.project.viewModel.MedicineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletDetailScreen(
    viewModel: MedicineViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val tablet by viewModel.selectedTablet.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Название Таблетки") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←") // Или Icon(Icons.Default.ArrowBack...)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Заголовок
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Text(
                    text = tablet?.name ?: "Не выбрано",
                    modifier = Modifier.padding(20.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Text("Информация", fontWeight = FontWeight.Bold)

            // Описание
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE))
            ) {
                Text(
                    text = tablet?.description ?: "",
                    modifier = Modifier.padding(16.dp)
                )
            }

            Text("Совместимость", fontWeight = FontWeight.Bold)

            tablet?.compatibility?.forEach { info ->
                CompatibilityItem(info)
            }
        }
    }
}

@Composable
fun CompatibilityItem(info: com.example.project.data.model.CompatibilityInfo) {
    val color = when (info.status) {
        CompatibilityStatus.COMPATIBLE -> Color(0xFF4CAF50)
        CompatibilityStatus.INCOMPATIBLE -> Color(0xFFF44336)
        CompatibilityStatus.CAUTION -> Color(0xFFFF9800)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = info.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = info.description, color = color)
        }
    }
}