package com.example.cpw_253_adassanctuary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.cpw_253_adassanctuary.ui.theme.CPW253AdasSanctuaryTheme

class MainActivity : ComponentActivity() { // 앱이 실행될때 처음 시작되는 화면
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Jetpack Compose로 UI를 그리는 시작점
            CPW253AdasSanctuaryTheme { // 앱의 전체 색상·폰트 등 테마 적용
                Surface(modifier = Modifier.fillMaxSize()) { // 배경 깔기용 UI 컴포넌트
                    MoodEntryScreen() // 기분 입력 화면
                }
            }
        }
    }
}

@Composable // Compose UI 요소를 반환한다는 표시
fun MoodEntryScreen() { // 직접 만든 화면 UI 함수(기분 입력 화면)
    var selectedMood by remember { mutableStateOf("") } // 사용자가 어떤 이모지를 눌렀는지 저장, 값이 바뀌면 Compose가 자동으로 화면 다시 그림
    var memo by remember { mutableStateOf("") } // 사용자가 입력한 메모를 저장

    // UI 구성 시작
    Column(modifier = Modifier.padding(16.dp)) { //  세로 방향으로 UI를 쌓는 레이아웃
        Text("How are you feeling today?", style = MaterialTheme.typography.titleLarge) // 질문 출력 (폰트는 테마에서 지정된 titleLarge 스타일 사용)
        Spacer(modifier = Modifier.height(8.dp))

        Row { // 가로 방향으로 배치
            listOf("😀", "😐", "😢", "😠").forEach { mood -> // 이모지 목록을 순회하며 버튼 생성
                Button( // 클릭하면 selectedMood 상태가 바뀜
                    onClick = { selectedMood = mood },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(mood, fontSize = 24.sp) // 이모지를 버튼 안에 표시
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 메모 입력창
        OutlinedTextField( // 외곽선 있는 텍스트 입력창
            value = memo, // 현재 메모 값
            onValueChange = { memo = it }, // 입력 내용이 바뀔 때마다 memo 상태 업데이트
            label = { Text("Today's Note") },
            modifier = Modifier.fillMaxWidth() // 화면 너비 전체 사용
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 저장 버튼
        Button(
            onClick = { // 버튼 누르면 콘솔에 기분/메모 출력 (현재는 저장은 안 되고 그냥 확인용)
                println("Feeling: $selectedMood / Note: $memo")
            },
            enabled = selectedMood.isNotEmpty(), // 기분이 선택돼 있어야 버튼이 활성화됨
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

// Preview Function (Android Studio 미리보기 패널에서 이 화면을 보여주게 함)
@Preview(showBackground = true)
@Composable
fun MoodEntryPreview() {
    CPW253AdasSanctuaryTheme {
        MoodEntryScreen()
    }
}
