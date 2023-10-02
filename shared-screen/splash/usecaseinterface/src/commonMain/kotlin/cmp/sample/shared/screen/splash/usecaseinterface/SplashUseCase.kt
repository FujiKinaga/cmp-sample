package cmp.sample.shared.screen.splash.usecaseinterface

interface SplashUseCase {

  suspend fun init(): Result<Boolean>
}
