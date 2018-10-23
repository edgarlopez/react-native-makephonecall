
# react-native-makephonecall

## Getting started

`$ npm install react-native-makephonecall --save`

### Mostly automatic installation

`$ react-native link react-native-makephonecall`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-make-phone-call` and add `RNMakePhoneCall.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMakePhoneCall.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.repartamos.makephonecall;` to the imports at the top of the file
  - Add `new RNMakePhoneCallPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-makephonecall'
  	project(':react-native-makephonecall').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-makephonecall/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-makephonecall')
  	```

## Usage
```javascript
import Phone from 'react-native-makephonecall';

Phone.makeCall("88855443322");
```
