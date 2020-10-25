package br.com.atendimento.config;

import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.atendimento.domain.AtendimentoProcedimento;
import br.com.atendimento.domain.Medico;
import br.com.atendimento.domain.Paciente;
import br.com.atendimento.dto.MedicoDTO;
import br.com.atendimento.dto.PacienteDTO;
import br.com.atendimento.dto.ProcedimentoDTO;

@Configuration
public class ModelMapperConfiguration {

	@Bean
    public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		buildProcedimentoToProcedimentoDto(modelMapper);
		buildPacienteToPacienteDto(modelMapper);
		buildPacienteDtoToPaciente(modelMapper);
		buildMedicoToMedicoDto(modelMapper);
		buildMedicoDtoToMedico(modelMapper);
		
		
		buildCollections(modelMapper);
		
        return modelMapper;
    }

	private void buildProcedimentoToProcedimentoDto(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<AtendimentoProcedimento, ProcedimentoDTO>() {
			protected void configure() {
				map().setCodigo(source.getProcedimento().getCodigo());
				map().setId(source.getProcedimento().getId());
				map().setDescricao(source.getProcedimento().getDescricao());
				map().setQuantidade(source.getQuantidade());
			}
		});
	}

	private void buildCollections(ModelMapper modelMapper) {
		modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
			@Override
			public boolean applies(MappingContext<Object, Object> context) {
				 if(context.getSource() instanceof PersistentCollection) {
					try {
						Hibernate.initialize((PersistentCollection) context.getSource());
						return true;
					} catch (Exception e) {
						return false;
					}
				 }else {
					 return true;
				 }
			}});
	}
	
	private void buildMedicoDtoToMedico(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<MedicoDTO, Medico>() {
		    protected void configure() {
		        map().setCpf(source.getCpf());
		    }
		});		
	}

	private void buildMedicoToMedicoDto(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<Medico, MedicoDTO>() {
			protected void configure() {
				map().setCpf(source.getCpf().getNumero());
			}
		});		
	}

	private void buildPacienteDtoToPaciente(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<PacienteDTO, Paciente>() {
		    protected void configure() {
		        map().setCpf(source.getCpf());
		    }
		});
	}

	private void buildPacienteToPacienteDto(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<Paciente, PacienteDTO>() {
			protected void configure() {
				map().setCpf(source.getCpf().getNumero());
			}
		});
	}
}
