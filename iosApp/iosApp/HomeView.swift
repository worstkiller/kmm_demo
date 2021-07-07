//
//  HomeView.swift
//  iosApp
//
//  Created by Vikas on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import sharedApp

struct HomeView: View {
    
    @Binding var baseViewState: BaseViewState?
    @Binding var doggoResponseModels: [DoggoResponseModel]
    
    var body: some View {
        
        VStack {
            
            switch baseViewState {
            
            case is BaseViewState.LOADING:
                ProgressView("")
                
            case is BaseViewState.ERROR:
                let error = baseViewState as? BaseViewState.ERROR
                Text("\(error?.message ?? ""): \(error?.errorCode ?? 101)")
                
            case is BaseViewState.SUCCESS:
                List {
                    ForEach(doggoResponseModels, id: \.self){ doggo in
                        
                        SingleDogView(doggoModel: doggo)
                        
                    }
                }
 
            default:
                ProgressView("")
                
            }
            
        }
    }
    
}

// MARK: HomeView Preview
struct HomeView_Previews: PreviewProvider {
    static let dogElement = DoggoResponseModel(
        breed_group: "hello",
        image: DoggoImage(url: ""),
        life_span: "12",
        name: "Pitbull",
        origin: "peru",
        temperament: "angry",
        country_code: "PE"
    )
    static let doggoModel = [DoggoResponseModel](_immutableCocoaArray: dogElement)
    static var previews: some View {
        HomeView(baseViewState: .constant(nil), doggoResponseModels: .constant(doggoModel))
    }
}

// MARK: Single dog view
struct SingleDogView : View {
    
    let doggoModel: DoggoResponseModel
    
    var body: some View {
        
        HStack {
            
            WebImage(url: URL(string: productAdapterItem.image))
                .placeholder{
                    Color.fromHex(Colors.PRIMARY_COLOR)
                }
                .resizable()
                .frame(width: 60, height: 60, alignment: .center)
                .scaledToFill()
                .clipShape(RoundedRectangle(cornerRadius: 6))
                .shadow(radius: 2)
            
        }.background(RoundedRectangle(cornerRadius: 20).foregroundColor(.white).shadow(radius: 4))
        
    }
}
