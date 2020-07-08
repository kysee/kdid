package com.a2z.kchainlib

import com.a2z.kchainlib.tools.fromHex
import com.a2z.kchainlib.tools.randBytes
import com.a2z.kchainlib.tools.toHex
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.protobuf.ProtoBuf
import kotlinx.serialization.protobuf.ProtoId
import org.junit.Test
import org.junit.Assert.*
import java.math.BigInteger

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProtoBufUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @ImplicitReflectionSerializer
    @Test
    fun testTrxTransferProtobuf() {
        val tx = TrxTransfer(BigInteger("10000000000000000"), "Hello".toByteArray())
        val bz = tx.encode<TrxTransfer>()
        assertArrayEquals(fromHex("0A072386F26FC10000120548656C6C6F"), bz)
        val tx2 = TrxPayload.decode<TrxTransfer>(bz)
        assertEquals(tx, tx2)
    }

    @ImplicitReflectionSerializer
    @Test
    fun testTrxDataCreateProtobuf() {
        val authors = arrayOf(randBytes(20), randBytes(20), randBytes(20))
        val tx = TrxDataCreate("Hello".toByteArray(),
            authors,
            0
        )
        val bz = tx.encode<TrxDataCreate>()
        val tx2 = TrxPayload.decode<TrxDataCreate>(bz)
        assertEquals(tx, tx2)
    }
}