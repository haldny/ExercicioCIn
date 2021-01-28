package com.haldny.exerciciocin

import android.content.Context
import android.provider.ContactsContract

object BuscaContato {

    fun lerContatos(context: Context) : List<Contato> {
        val cursor = context.contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME)

        val indexID = cursor?.getColumnIndex(ContactsContract.Contacts._ID)

        val indexPhone = cursor?.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)

        val indexName = cursor?.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

        val contatos = mutableListOf<Contato>()

        while (cursor?.moveToNext() == true) {
            val id = indexID?.let { cursor?.getString(it) }
            val name = indexName?.let { cursor?.getString(it) }

            val phones = indexPhone?.let { cursor?.getString(it) }
            val phoneCount = phones?.toInt() ?: 0
            if (phoneCount > 0) {
                //TODO: Get Phones
            }

            val contato = Contato(id, name, listOf())
            contatos.add(contato)
        }

        cursor?.close()

        return contatos.sortedBy { contato -> contato.ID }
    }

}