/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cmp.sample.shared.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Cmp typography.
 */
internal val CmpType = Typography(
  displayLarge = TextStyle(
    fontWeight = FontWeight.ExtraBold,
    fontSize = 55.sp,
    lineHeight = 63.sp,
  ),
  displayMedium = TextStyle(
    fontWeight = FontWeight.ExtraBold,
    fontSize = 55.sp,
    lineHeight = 63.sp,
  ),
  displaySmall = TextStyle(
    fontWeight = FontWeight.ExtraBold,
    fontSize = 55.sp,
    lineHeight = 63.sp,
  ),
  headlineLarge = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
  ),
  headlineMedium = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
  ),
  headlineSmall = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
  ),
  titleLarge = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 24.sp,
  ),
  titleMedium = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 24.sp,
  ),
  titleSmall = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 24.sp,
  ),
  bodyLarge = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 22.sp,
  ),
  bodyMedium = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 22.sp,
  ),
  bodySmall = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 22.sp,
  ),
  labelLarge = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    lineHeight = 20.sp,
  ),
  labelMedium = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 20.sp,
  ),
  labelSmall = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 20.sp,
  ),
)
