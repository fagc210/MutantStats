# MutantStats
Este proyecto contiene una lambda de aws se conecta a una tabla en dynamoda.
se consulta los registros para luego se procesados entregando la siguiente información
cantidad de DAN mutante
cantidad de DAN humano 
relacion 

esta informacion se entrega json con la siguiente estructura

	{
		"count_mutant_dna": 2,
		"count_human_dna": 8,
		"ratio": 0.2
	}


**Esta funcionalidad esta desplegada en en AWS mediante un metodo GET en la siguiente url**

https://g328gu1tgh.execute-api.us-east-1.amazonaws.com/qa/stats

En este repositorio encontarás  una colección de postman con el nombre **mercadoLibreCollection** que te permitirá realizar peticiones a la ruta de aws	