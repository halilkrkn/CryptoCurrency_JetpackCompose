package com.halilkrkn.cryptocurrency.common

// Burada ne yapılıyor?
// Resource sınıfı, verileri alırken, verilerin durumunu kontrol etmek için kullanılıyor.
// Verilerin durumu, başarılı mı, başarısız mı, yükleniyor mu gibi durumlar.
// Bu durumları kontrol etmek için, Resource sınıfı kullanılıyor.
// Resource sınıfı, Success, Error ve Loading sınıflarından oluşuyor.
// Success sınıfı, verilerin başarılı bir şekilde alındığını gösteriyor.
// Error sınıfı, verilerin alınırken bir hata ile karşılaşıldığını gösteriyor.
// Loading sınıfı, verilerin alınırken yükleniyor olduğunu gösteriyor.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
// sealed class nedir?
// Sealed class, bir sınıfın, başka bir sınıf tarafından kalıtılmasını engellemek için kullanılıyor.
// Yani, sealed class, bir sınıfın, başka bir sınıf tarafından kalıtılmasını engelliyor.
// Sealed class, abstract class gibi davranıyor.
// Sealed class, abstract class gibi, nesne oluşturulamıyor.
// Sealed class, abstract class gibi, constructor içerebiliyor.

