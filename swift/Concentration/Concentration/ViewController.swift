//
//  ViewController.swift
//  Concentration
//
//  Created by liko on 2018/10/28.
//  Copyright © 2018 liko. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


    @IBAction func touchCard(_ sender: UIButton) {
        flipCard(withEmojo : "👻", on : sender)
    }
    
    func flipCard(withEmoji emoji : String, on Button : UIButton<#parameters#>) {
        <#function body#>
    }
}

