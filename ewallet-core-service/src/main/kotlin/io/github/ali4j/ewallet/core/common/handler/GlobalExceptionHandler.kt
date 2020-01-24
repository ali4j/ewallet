package io.github.ali4j.ewallet.core.common.handler

import io.github.ali4j.ewallet.core.common.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class GlobalExceptionHandler(@Autowired val messageSource: MessageSource) {

    val logger : Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)



    @ExceptionHandler(NotFoundException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun translateException(e: NotFoundException, request: HttpServletRequest): Error {
        return translateException(e)
    }


    fun translateException(exception: Exception): Error{
        val errorMessage = messageSource.getMessage(
                exception.javaClass.name, arrayOf<Any>(),
                LocaleContextHolder.getLocale())

        val errorItems = errorMessage.split("#".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        return Error(errorItems[1], errorItems[0])
    }





}
