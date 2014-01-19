<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table class="table">
	<tr>
		<td>Nombre</td>
		<td><input name="nombre" type="text"
			value="${user.datosUsuario[0].nombre}" required /></td>
		<td>Apellidos</td>
		<td><input name="apellidos" type="text"
			value="${user.datosUsuario[0].apellidos}" required /></td>
	</tr>
	<tr>
		<td>D.N.I.</td>
		<td><input name="dni" type="text"
			value="${user.datosUsuario[0].dni}" required /></td>
		<td>Fecha de nacimiento</td>
		<td><input name="fecha_nacimiento" type="date"
			value="<fmt:formatDate pattern="yyyy-MM-dd" 
            value="${user.datosUsuario[0].fechaNacimiento}" />"
			required /></td>
	</tr>
	<tr>
		<td>Teléfono</td>
		<td><input name="telefono" type="text"
			value="${user.datosUsuario[0].telefono}" required /></td>
		<td>Teléfono móvil</td>
		<td><input name="telefonoOpcional" type="text"
			value="${user.datosUsuario[0].telefonoOpcional}" /></td>
	</tr>
	<tr>
		<td>Dirección</td>
		<td><input name="direccion" type="text"
			value="${user.datosUsuario[0].direccion}" required /></td>
		<td>C.P.</td>
		<td><input name="codigoPostal" type="text"
			value="${user.datosUsuario[0].codigoPostal}" required /></td>
	</tr>
	<tr>
		<td>Provincia</td>
		<td><input name="provincia" type="text"
			value="${user.datosUsuario[0].provincia}" required /></td>
		<td>Localidad</td>
		<td><input name="localidad" type="text"
			value="${user.datosUsuario[0].localidad}" required /></td>
	</tr>
</table>

