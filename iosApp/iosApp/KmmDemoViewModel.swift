//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Vikas on 06/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import sharedApp

class KmmDemoViewModel: ObservableObject {
    
    @Published var screen : Navigation = Navigation.splash
    
    @Published var doggoList: [DoggoResponseModel] = []
    
    @Published var apiStatus: BaseViewState? = nil
    
    private lazy var doggoListRepository: DoggoListRepositoryImpl = {
        return DoggoListRepositoryImpl()
    }()
    
    private func getDoggoList(){
        doggoListRepository.getDoggoList(count: 15){ data in
            
            DispatchQueue.main.async {
                
                switch data.status {
                
                case  is BaseViewState.ERROR :
                    //do something here on error
                    self.apiStatus = data.status
                    
                case  is BaseViewState.LOADING :
                    //do something here on loading
                    self.apiStatus = data.status
                    
                case is BaseViewState.SUCCESS :
                    //process data received
                    self.apiStatus = data.status
                    self.doggoList = data.data as? [DoggoResponseModel] ?? []
                
                default:
                    self.apiStatus = data.status
                }
                
            }
        }
        
    }
    
}
