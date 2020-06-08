package com.ikarimeister.mycatplayer

sealed class Filter

object NoFilter : Filter()

class TypeFilter(val type: MediaType) : Filter()