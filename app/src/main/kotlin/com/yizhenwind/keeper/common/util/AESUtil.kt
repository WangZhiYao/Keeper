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

    init {
        val keyStore = getKeyStore()
        if (!keyStore.containsAlias(KEY_ALIAS)) {
            generateKeyStore()
        }
    }

    private fun getKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
        keyStore.load(null)
        return keyStore
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
        val keyStore = getKeyStore()
        return keyStore.getKey(KEY_ALIAS, null) as? SecretKey
            ?: throw IllegalStateException("No key found under alias: $KEY_ALIAS")
    }

    @JvmStatic
    fun encryptData(data: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), IvParameterSpec(IV.encodeToByteArray()))
        val encryptedBytes = cipher.doFinal(data.encodeToByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    @JvmStatic
    fun decryptData(encryptedData: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), IvParameterSpec(IV.encodeToByteArray()))
        val decryptedBytes = cipher.doFinal(Base64.decode(encryptedData, Base64.DEFAULT))
        return String(decryptedBytes, Charsets.UTF_8)
    }

    fun deleteKeyPair(alias: String) {
        val keyStore = getKeyStore()
        keyStore.deleteEntry(alias)
    }
}
