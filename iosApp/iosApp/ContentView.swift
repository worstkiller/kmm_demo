import SwiftUI
import sharedApp

struct ContentView: View {
    
    @StateObject var kmmViewModel =  KmmDemoViewModel()
    
    var body: some View {
        
        VStack {
            switch kmmViewModel.screen {
            
            case .splash:
                SplashView(kmmViewModel: kmmViewModel)
          
            case .home:
                HomeView(kmmViewModel: kmmViewModel)
            
            default:
                SplashView(kmmViewModel: kmmViewModel)
            }
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
