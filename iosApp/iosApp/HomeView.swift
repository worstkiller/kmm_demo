//
//  HomeView.swift
//  iosApp
//
//  Created by Vikas on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import sharedApp
import SDWebImageSwiftUI

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
                ScrollView {
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
    static var previews: some View {
        HomeView(baseViewState: .constant(BaseViewState.SUCCESS.init()), doggoResponseModels: .constant(KmmDemoViewModel.getTestData()))
    }
}

// MARK: Single dog view
struct SingleDogView : View {
    
    let doggoModel: DoggoResponseModel
    
    var body: some View {
        
        HStack {
            
            WebImage(url: URL(string: doggoModel.image.url))
                .placeholder{
                    Color.fromHex("#311b92")
                }
                .resizable()
                .scaledToFill()
                .frame(width: 100, height: 120, alignment: .center)
                .clipShape(RoundedRectangle(cornerRadius: 20)).padding(.all, 12)
            
            Spacer().frame(width: 8)
            
            VStack(alignment: .leading) {
                
                Text(doggoModel.name).font(.headline).fontWeight(.semibold).foregroundColor(.gray).padding(.bottom, 2)
                
                Text("Breed:  \(doggoModel.breed_group ?? "NA")").foregroundColor(Color.gray.opacity(0.6)).padding(.bottom, 2)
                
                Text("Life span:  \(doggoModel.life_span)").foregroundColor(Color.gray.opacity(0.6)).padding(.bottom, 2)
                
                Text("Origin:  \(doggoModel.origin ?? "NA")").foregroundColor(Color.gray.opacity(0.6))
                
                Spacer()
                
            }.frame(height: 120)
            
            Spacer()
            
        }.background(RoundedRectangle(cornerRadius: 20).foregroundColor(.white).shadow(radius: 4)).padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
        
    }
}
