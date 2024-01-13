package com.namnp.jetpack_compose.util

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@ExperimentalPermissionsApi
fun PermissionState.isDeniedPermanently() = !status.shouldShowRationale && !status.isGranted