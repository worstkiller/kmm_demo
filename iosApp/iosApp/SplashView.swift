//
//  SplashView.swift
//  iosApp
//
//  Created by Vikas on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import sharedApp

struct SplashView: View {
    
    @StateObject var kmmViewModel: KmmDemoViewModel
    
    var body: some View {
        ZStack {
            
            Color.fromHex("#311b92")
            
            Image("paw_print").resizable().foregroundColor(.white).frame(width: 60, height: 60)
            
            
        }.ignoresSafeArea().onAppear{
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 3) {
                
                withAnimation {
                    
                    self.kmmViewModel.screen = Navigation.home
                    
                }
            }
            
        }
    }
}

struct SplashView_Previews: PreviewProvider {
    static var previews: some View {
        SplashView(kmmViewModel: KmmDemoViewModel())
    }
}
