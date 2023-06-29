package com.yizhenwind.keeper.common.util

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/23
 */
object AESUtil {

    private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
    private const val KEY_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    private const val TRANSFORMATION = "$KEY_ALGORITHM/$BLOCK_MODE/$PADDING"
    private const val KEY_ALIAS = "Keeper"
    private const val IV = "qZ8G5vfVfmMfYvp4"

    private val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        .apply { load(null) }

    init {
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            generateKeyStore()
        }
    }

    private fun generateKeyStore() {
        val keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM)
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(BLOCK_MODE)
            .setEncryptionPaddings(PADDING)
            .setRandomizedEncryptionRequired(false)
            .build()
        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }

    private fun getSecretKey(): SecretKey {
        return keyStore.getKey(KEY_ALIAS, null) as? SecretKey
            ?: throw IllegalStateException("No key found under alias: $KEY_ALIAS")
    }

    @JvmStatic
    fun encryptData(plaintext: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), IvParameterSpec(IV.encodeToByteArray()))
        val encryptedBytes = cipher.doFinal(plaintext.encodeToByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)
    }

    @JvmStatic
    fun decryptData(ciphertext: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), IvParameterSpec(IV.encodeToByteArray()))
        val decryptedBytes = cipher.doFinal(Base64.decode(ciphertext, Base64.NO_WRAP))
        return String(decryptedBytes, Charsets.UTF_8)
    }

    fun deleteKeyPair(alias: String) {
        keyStore.deleteEntry(alias)
    }
}
