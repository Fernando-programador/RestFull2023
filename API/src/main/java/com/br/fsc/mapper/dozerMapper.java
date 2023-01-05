package com.br.fsc.mapper;

public class dozerMapper {
private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static<O, D> D parseObject(O origin, Class<D> detination) {
		return mapper.map(origin, detination);
	}

	
	public static <O, D> List<D> parseListObject(List<O> origin, Class<D> destination){
		
		List<D> destinationObjects = new ArrayList<>();
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
			
		}
		return destinationObjects;
	}

}
