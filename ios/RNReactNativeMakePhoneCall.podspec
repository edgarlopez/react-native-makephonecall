
Pod::Spec.new do |s|
  s.name         = "RNReactNativePhoneCall"
  s.version      = "1.0.0"
  s.summary      = "RNReactNativePhoneCall"
  s.description  = <<-DESC
                  RNReactNativePhoneCall
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author       = { "Edgar Lopez" => "inge.edgarlopez@gmail.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/edgarlopez/react-native-phone-call.git", :tag => "master" }
  s.source_files  = "RNReactNativePhoneCall/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end
