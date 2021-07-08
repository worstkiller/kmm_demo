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
                HomeView(baseViewState: $kmmViewModel.apiStatus, doggoResponseModels: $kmmViewModel.doggoList)
                
            default:
                SplashView(kmmViewModel: kmmViewModel)
            }
        }.onAppear{
            
            self.kmmViewModel.getDoggoList()
            
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
