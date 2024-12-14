import SwiftUI
import encrypt

struct ContentView: View {
	let encrypt = Greeting().encrypt()
    let decrypt = Greeting().decrypt()

	var body: some View {
        Grid{
            Text("Encrypt")
            Text(encrypt)
            Text("Decrypt")
                .padding(.top, 20)
            Text(decrypt)
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
