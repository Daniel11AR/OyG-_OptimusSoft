package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.dao.ComprobanteDao;
import com.prueba.OyG_OPTIMUS.models.Comprobante;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComprobanteValidatorImp implements IComprobanteValidator{

    @Autowired
    private ComprobanteDao comprobanteDao;
    @Override
    public void validator(Comprobante comprobante) throws ApiUnprocessableEntity {

        //Validamos que ninguno de los campos obligatorios quede vacío
        if(comprobante.getConcepto()==null || comprobante.getConcepto().isEmpty()){
            message("El concepto del comprobante no puede estar vacío.");
        }
        if(comprobante.getFecha()==null || comprobante.getFecha().toString().isEmpty()){
            message("El campo 'Fecha' no puede quedar vacío, debes ingresar la fecha en la que se generó el comprobante.");
        }
        if(comprobante.getIva()==null || comprobante.getIva().toString().isEmpty()){
            message("El campo 'IVA' no puede quedar vacío.");
        }
        if(comprobante.getNFactura()==null){
            message("El número de la factura es obligatorio.");
        }
        if(comprobante.getIdentificacion()==null || comprobante.getIdentificacion().isEmpty()){
            message("No puedes dejar el campo de Identificación de la persona asociada al comprobante en blanco.");
        }
        if(comprobante.getNumeroComprobante()==null || comprobante.getNumeroComprobante().toString().isEmpty()){
            message("No puedes dejar el campo de 'Número de comprobante' vacío.");
        }
        if(comprobante.getPorcentajeIva()==null || comprobante.getPorcentajeIva().toString().isEmpty()){
            message("El campo de Porcentaje de IVA no puede ir vacío.");
        }
        if(comprobante.getPorcentajeRetencion()==null || comprobante.getPorcentajeRetencion().toString().isEmpty()){
            message("El campo de Porcentaje de retención no puede ir vacío.");
        }
        if(comprobante.getRetencion()==null || comprobante.getRetencion().toString().isEmpty()){
            message("El campo Retención no puede ir vacío.");
        }
        if(comprobante.getSubTotal()==null || comprobante.getSubTotal().toString().isEmpty()){
            message("No puedes dejar el campo de subtotal vacío.");
        }
        if(comprobante.getTipoPago()==null || comprobante.getTipoPago().isEmpty()){
            message("No puedes dejar el Tipo de pago del comprobante vacío.");
        }
        if(comprobante.getValorNeto()==null || comprobante.getValorNeto().toString().isEmpty()){
            message("El Valor Neto del comprobante no puede ir vacío.");
        }
        if(comprobante.getTipoComprobante()==null || comprobante.getTipoComprobante().isEmpty()){
            message("El campo de tipo de comprobante no puede ir vacío.");
        }
        if(comprobante.getCiudad()==null || comprobante.getCiudad().isEmpty()){
            message("No puedes dejar el campo de ciudad vacío.");
        }
        if(comprobante.getEstadoComprobante()==null || comprobante.getEstadoComprobante().isEmpty()){
            message("No puedes ingresar un estado de comprobante vacío.");
        }

        //Validaciones para el campo 'Concepto'
        if(comprobante.getConcepto().length()<4){
            message("El campo concepto debe tener por lo menos 4 caracteres.");
        }
        if(comprobante.getConcepto().length()>254){
            message("El campo concepto no puede exceder los 250 caracteres permitidos.");
        }

        //Validaciones para el campo 'IVA'
        if(comprobante.getIva()<0){
            message("No puedes ingresar un valor negativo para el IVA.");
        }

        if(!(comprobante.getIva().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo IVA.");
        }

        //Validaciones para el campo Número de Factura.
        if(comprobante.getNFactura().toString().length()<3){
            message("Es obligatorio que el número de la factura tenga por lo menos 3 caracteres.");
        }
        if(comprobante.getNFactura()<0){
            message("El número de factura que ingresaste no puede ser una cantidad negativa.");
        }
        if(!(comprobante.getNFactura().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Número de Factura.");
        }
        for(Comprobante c: comprobanteDao.findAll()){
            if(c.getNFactura().equals(comprobante.getNFactura())){
                message("Ya existe un comprobante en el sistema con este Número de Factura.");
            }
        }

        //Validaciones para el campo Identificación
        if(comprobante.getIdentificacion().length()<4){
            message("El campo Identificación debe tener como mínimo 4 caracteres.");
        }
        if(comprobante.getIdentificacion().length()>50){
            message("El campo Identificación no puede exceder los 50 caracteres permitidos.");
        }
        boolean ide = true;
        int guion=0;
        for(int i=0;i<comprobante.getIdentificacion().length(); i++){
            char letra = comprobante.getIdentificacion().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z' || letra == ' '){
                ide=false;
            }
            if(letra=='-'){
                guion++;
            }
        }
        if(ide==false || guion>1){
            message("El campo Identificación no puede tener letras. Solo números, un guión y sin espacios.");
        }

        //Validaciones para el campo de Número de comprobante
        if(comprobante.getNumeroComprobante()<0){
            message("El número de comprobante no puede ser una cantidad negativa.");
        }
        if(!(comprobante.getNumeroComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Número de Comprobante.");
        }
        for(Comprobante c: comprobanteDao.findAll()){
            if(c.getNumeroComprobante().equals(comprobante.getNumeroComprobante())){
                message("Ya existe un comprobante en el sistema con este mismo Número de Comprobante.");
            }
        }

        //Validaciones para el campo Porcentaje de IVA
        try{
            double procentaje  = Double.parseDouble(comprobante.getPorcentajeIva().toString());
        }catch (Exception e){
            message("Es obligatorio ingresar un número decimal en el campo Porcentaje de IVA");
        }
        if(!(comprobante.getPorcentajeIva().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Porcentaje de IVA.");
        }

        //validaciones para el campo Porcentaje de Retención
        try{
            double procentaje  = Double.parseDouble(comprobante.getPorcentajeRetencion().toString());
        }catch (Exception e){
            message("Es obligatorio ingresar un número decimal en el campo Porcentaje de Retención");
        }
        if(!(comprobante.getPorcentajeRetencion().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Porcentaje de Retención.");
        }

        //Validaciones para el campo Recibido de y pagado A
        if(comprobante.getRecibidoDe()==null && comprobante.getPagadoA()==null){
            message("Es obligatorio indicar de quién viene el comprobate económico o hacia quién va dirigido.");
        }

        if(comprobante.getRecibidoDe().length()>100 || comprobante.getPagadoA().length()>100){
            message("El campo 'Recibido de' y el campo 'Pagado A' no pueden exceder los 100 caracteres permitidos.");
        }

        //Validaciones para el campo Retención
        if(!(comprobante.getRetencion().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo caracteres numéricos en el campo Retención.");
        }
        if(comprobante.getPorcentajeRetencion()<0){
            message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Retención.");
        }

        //Validaciones para el campo Subtotal
        if(!(comprobante.getSubTotal().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo caracteres numéricos en el campo Subtotal.");
        }
        if(comprobante.getSubTotal()<0){
            message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo subtotal.");
        }

        //Validaciones para el campo tipo de pago
        boolean pago = true;
        for(int i=0;i<comprobante.getTipoPago().length(); i++){
            char letra = comprobante.getTipoPago().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                pago=false;
            }
        }
        if(pago==false){
            message("El campo Tipo de Pago no puede contener números.");
        }
        if(comprobante.getTipoPago().length()<4){
            message("Es obligatorio que el campo Tipo de Pago contenga por lo menos 4 caracteres.");
        }
        if(comprobante.getRecibidoDe().length()>40){
            message("El campo 'Tipo de pago' no puede exceder los 40 caracteres permitidos.");
        }

        //Validaciones para el campo Valor Neto
        if(!(comprobante.getValorNeto().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo caracteres numéricos en el campo Valor Neto.");
        }
        if(comprobante.getValorNeto()<0){
            message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Valor Neto.");
        }

        //Validaciones para el campo Tipo Comprobante
        boolean tipo = true;
        for(int i=0;i<comprobante.getTipoComprobante().length(); i++){
            char letra = comprobante.getTipoComprobante().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                tipo=false;
            }
        }
        if(tipo==false){
            message("El campo Tipo de Comprobante no puede contener números.");
        }
        if(comprobante.getTipoComprobante().length()<4){
            message("Es obligatorio que el campo Tipo de Comprobante contenga por lo menos 4 caracteres.");
        }

        if(comprobante.getTipoComprobante().length()>40){
            message("Es obligatorio que el campo Tipo de Comprobante no exceda los 40 caracteres permitidos.");
        }

        //Validaciones para el campo Ciudad
        boolean ciudad = true;
        for(int i=0;i<comprobante.getCiudad().length(); i++){
            char letra = comprobante.getCiudad().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                ciudad=false;
            }
        }
        if(ciudad==false){
            message("El campo Ciudad no puede contener números.");
        }
        if(comprobante.getCiudad().length()<4){
            message("Es obligatorio que el campo Ciudad contenga por lo menos 4 caracteres.");
        }
        if(comprobante.getCiudad().length()>40){
            message("El campo 'Ciudad' no puede exceder los 40 caracteres permitidos.");
        }

    }

    @Override
    public void validatorActualizar(Comprobante comprobante) throws ApiUnprocessableEntity {

        //Validamos que ninguno de los campos obligatorios quede vacío
        if(comprobante.getConcepto()==null || comprobante.getConcepto().isEmpty()){
            message("El concepto del comprobante no puede estar vacío.");
        }
        if(comprobante.getFecha()==null || comprobante.getFecha().toString().isEmpty()){
            message("El campo 'Fecha' no puede quedar vacío, debes ingresar la fecha en la que se generó el comprobante.");
        }
        if(comprobante.getIva()==null || comprobante.getIva().toString().isEmpty()){
            message("El campo 'IVA' no puede quedar vacío.");
        }
        if(comprobante.getNFactura()==null){
            message("El número de la factura es obligatorio.");
        }
        if(comprobante.getIdentificacion()==null || comprobante.getIdentificacion().isEmpty()){
            message("No puedes dejar el campo de Identificación de la persona asociada al comprobante en blanco.");
        }
        if(comprobante.getNumeroComprobante()==null || comprobante.getNumeroComprobante().toString().isEmpty()){
            message("No puedes dejar el campo de 'Número de comprobante' vacío.");
        }
        if(comprobante.getPorcentajeIva()==null || comprobante.getPorcentajeIva().toString().isEmpty()){
            message("El campo de Porcentaje de IVA no puede ir vacío.");
        }
        if(comprobante.getPorcentajeRetencion()==null || comprobante.getPorcentajeRetencion().toString().isEmpty()){
            message("El campo de Porcentaje de retención no puede ir vacío.");
        }
        if(comprobante.getRetencion()==null || comprobante.getRetencion().toString().isEmpty()){
            message("El campo Retención no puede ir vacío.");
        }
        if(comprobante.getSubTotal()==null || comprobante.getSubTotal().toString().isEmpty()){
            message("No puedes dejar el campo de subtotal vacío.");
        }
        if(comprobante.getTipoPago()==null || comprobante.getTipoPago().isEmpty()){
            message("No puedes dejar el Tipo de pago del comprobante vacío.");
        }
        if(comprobante.getValorNeto()==null || comprobante.getValorNeto().toString().isEmpty()){
            message("El Valor Neto del comprobante no puede ir vacío.");
        }
        if(comprobante.getTipoComprobante()==null || comprobante.getTipoComprobante().isEmpty()){
            message("El campo de tipo de comprobante no puede ir vacío.");
        }
        if(comprobante.getCiudad()==null || comprobante.getCiudad().isEmpty()){
            message("No puedes dejar el campo de ciudad vacío.");
        }
        if(comprobante.getEstadoComprobante()==null || comprobante.getEstadoComprobante().isEmpty()){
            message("No puedes ingresar un estado de comprobante vacío.");
        }

        //Validaciones para el campo 'Concepto'
        if(comprobante.getConcepto().length()<4){
            message("El campo concepto debe tener por lo menos 4 caracteres.");
        }
        if(comprobante.getConcepto().length()>254){
            message("El campo concepto no puede exceder los 250 caracteres permitidos.");
        }

        //Validaciones para el campo 'IVA'
        if(comprobante.getIva()<0){
            message("No puedes ingresar un valor negativo para el IVA.");
        }

        if(!(comprobante.getIva().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo IVA.");
        }

        //Validaciones para el campo Número de Factura.
        if(comprobante.getNFactura().toString().length()<3){
            message("Es obligatorio que el número de la factura tenga por lo menos 3 caracteres.");
        }
        if(comprobante.getNFactura()<0){
            message("El número de factura que ingresaste no puede ser una cantidad negativa.");
        }
        if(!(comprobante.getNFactura().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Número de Factura.");
        }
        for(Comprobante c: comprobanteDao.findAll()){
            if(!(comprobante.getId().equals(c.getId())))
                if(c.getNFactura().equals(comprobante.getNFactura()))
                    message("Ya existe un comprobante en el sistema con este Número de Factura.");
        }

        //Validaciones para el campo Identificación
        if(comprobante.getIdentificacion().length()<4){
            message("El campo Identificación debe tener como mínimo 4 caracteres.");
        }
        if(comprobante.getIdentificacion().length()>50){
            message("El campo Identificación no puede exceder los 50 caracteres permitidos.");
        }
        boolean ide = true;
        int guion=0;
        for(int i=0;i<comprobante.getIdentificacion().length(); i++){
            char letra = comprobante.getIdentificacion().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z' || letra == ' '){
                ide=false;
            }
            if(letra=='-'){
                guion++;
            }
        }
        if(ide==false || guion>1){
            message("El campo Identificación no puede tener letras. Solo números, un guión y sin espacios.");
        }

        //Validaciones para el campo de Número de comprobante
        if(comprobante.getNumeroComprobante()<0){
            message("El número de comprobante no puede ser una cantidad negativa.");
        }
        if(!(comprobante.getNumeroComprobante().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Número de Comprobante.");
        }
        for(Comprobante c: comprobanteDao.findAll()){
            if(!(comprobante.getId().equals(c.getId())))
                if(c.getNumeroComprobante().equals(comprobante.getNumeroComprobante()))
                    message("Ya existe un comprobante en el sistema con este mismo Número de Comprobante.");
        }

        //Validaciones para el campo Porcentaje de IVA
        try{
            double procentaje  = Double.parseDouble(comprobante.getPorcentajeIva().toString());
        }catch (Exception e){
            message("Es obligatorio ingresar un número decimal en el campo Porcentaje de IVA");
        }
        if(!(comprobante.getPorcentajeIva().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Porcentaje de IVA.");
        }

        //validaciones para el campo Porcentaje de Retención
        try{
            double procentaje  = Double.parseDouble(comprobante.getPorcentajeRetencion().toString());
        }catch (Exception e){
            message("Es obligatorio ingresar un número decimal en el campo Porcentaje de Retención");
        }
        if(!(comprobante.getPorcentajeRetencion().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo números en el campo Porcentaje de Retención.");
        }

        //Validaciones para el campo Recibido de y pagado A
        if(comprobante.getRecibidoDe()==null && comprobante.getPagadoA()==null){
            message("Es obligatorio indicar de quién viene el comprobate económico o hacia quién va dirigido.");
        }

        if(comprobante.getRecibidoDe().length()>100 || comprobante.getPagadoA().length()>100){
            message("El campo 'Recibido de' y el campo 'Pagado A' no pueden exceder los 100 caracteres permitidos.");
        }

        //Validaciones para el campo Retención
        if(!(comprobante.getRetencion().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo caracteres numéricos en el campo Retención.");
        }
        if(comprobante.getPorcentajeRetencion()<0){
            message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Retención.");
        }

        //Validaciones para el campo Subtotal
        if(!(comprobante.getSubTotal().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo caracteres numéricos en el campo Subtotal.");
        }
        if(comprobante.getSubTotal()<0){
            message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo subtotal.");
        }

        //Validaciones para el campo tipo de pago
        boolean pago = true;
        for(int i=0;i<comprobante.getTipoPago().length(); i++){
            char letra = comprobante.getTipoPago().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                pago=false;
            }
        }
        if(pago==false){
            message("El campo Tipo de Pago no puede contener números.");
        }
        if(comprobante.getTipoPago().length()<4){
            message("Es obligatorio que el campo Tipo de Pago contenga por lo menos 4 caracteres.");
        }
        if(comprobante.getTipoPago().length()>40){
            message("Es obligatorio que el campo Tipo de Pago no exceda los 40 caracteres permitidos.");
        }

        //Validaciones para el campo Valor Neto
        if(!(comprobante.getValorNeto().toString().matches("[+-]?\\d*(\\.\\d+)?"))){
            message("Es obligatorio ingresar solo caracteres numéricos en el campo Valor Neto.");
        }
        if(comprobante.getValorNeto()<0){
            message("Es obligatorio ingresar cantidades iguales o mayores a cero(0) en el campo Valor Neto.");
        }

        //Validaciones para el campo Tipo Comprobante
        boolean tipo = true;
        for(int i=0;i<comprobante.getTipoComprobante().length(); i++){
            char letra = comprobante.getTipoComprobante().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                tipo=false;
            }
        }
        if(tipo==false){
            message("El campo Tipo de Comprobante no puede contener números.");
        }
        if(comprobante.getTipoComprobante().length()<4){
            message("Es obligatorio que el campo Tipo de Comprobante contenga por lo menos 4 caracteres.");
        }
        if(comprobante.getTipoComprobante().length()>40){
            message("Es obligatorio que el campo Tipo de Comprobante no exceda los 40 caracteres permitidos.");
        }

        //Validaciones para el campo Ciudad
        boolean ciudad = true;
        for(int i=0;i<comprobante.getCiudad().length(); i++){
            char letra = comprobante.getCiudad().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                ciudad=false;
            }
        }
        if(ciudad==false){
            message("El campo Ciudad no puede contener números.");
        }
        if(comprobante.getCiudad().length()<4){
            message("Es obligatorio que el campo Ciudad contenga por lo menos 4 caracteres.");
        }
        if(comprobante.getCiudad().length()>40){
            message("El campo 'Ciudad' no puede exceder los 40 caracteres permitidos.");
        }

    }


    private void message(String message) throws ApiUnprocessableEntity{
        throw new ApiUnprocessableEntity(message);
    }
}
